package com.huayu.core.service.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huayu.core.HuayuConstants;
import com.huayu.core.annotation.PermissionsResource;
import com.huayu.core.bean.vo.Permissions;
import com.huayu.core.bean.vo.PermissionsList;
import com.huayu.core.bean.vo.RolePermissionsRef;
import com.huayu.core.bean.vo.User;
import com.huayu.core.bean.vo.UserRole;
import com.huayu.core.bean.vo.UserRoleRef;
import com.huayu.core.config.PermissionsConfiguer;
import com.huayu.core.config.SystemConfiguer;
import com.huayu.core.dao.mapper.PermissionsMapper;
import com.huayu.core.dao.mapper.RolePermissionsRefMapper;
import com.huayu.core.dao.mapper.UserMapper;
import com.huayu.core.dao.mapper.UserRoleMapper;
import com.huayu.core.dao.mapper.UserRoleRefMapper;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.core.security.IAsymmetric;
import com.huayu.core.service.ISystemService;
import com.huayu.core.util.CommonUtil;
import com.huayu.core.util.LoadClassUtil;

@Service
public class SystemService extends BaseService implements ISystemService {
	private static String SEPARATOR=File.separator;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleRefMapper userRoleRefMapper;
	@Autowired
	private PermissionsMapper permissionsMapper;
	@Autowired
	private RolePermissionsRefMapper rolePermissionsRefMapper;
	
	@Autowired
	private LoadClassUtil loadClassUtil;
	
	@Autowired
	private PermissionsConfiguer permissionsConfiguer;
	@Autowired
	private SystemConfiguer systemConfiguer;
	@Resource(name="md5")
	private IAsymmetric md5;
	
	/**
	 * 初始化系统信息
	 */
	public void initSystemInfo() throws ApplicationException{
		//初始化系统配置
		systemConfiguer.init();
		
		//检查定义的异常Code是否存在重复
		checkExCodeRepetition();
		
		//获取系统默认初始化角色列表
		List<UserRole> defaultRoles=permissionsConfiguer.getDefaultRoles();
		if(defaultRoles!=null && !defaultRoles.isEmpty()){
			for(UserRole ur: defaultRoles){
				if(userRoleMapper.checkExistsRole(ur)<=0){
					//如果不存在默认的角色，则新增
					userRoleMapper.insertRole(ur);
				}
			}
		}
		
		//获取初始化系统用户信息
		User defaultUser=permissionsConfiguer.getDefaultUser();
		if(defaultUser!=null && userMapper.checkExistsUser(defaultUser)<=0){
			//密码使用MD5加密
			defaultUser.setUserPassword(md5.encrypt(defaultUser.getUserPassword()));
			//系统默认用户不存在则新增
			userMapper.insertUser(defaultUser);
			
			//创建用户目录
			//用户作品目录
			CommonUtil.checkOrCreateFolder(SystemConfiguer.usersBaseLoacation+SEPARATOR+defaultUser.getId()+SEPARATOR+HuayuConstants.USER_PRODUCTION_FOLDER);
			//用户头像目录
			CommonUtil.checkOrCreateFolder(SystemConfiguer.usersBaseLoacation+SEPARATOR+defaultUser.getId()+SEPARATOR+HuayuConstants.USER_HEAD_ICO_FOLDER);
		}
		
		if(defaultUser.getId()==null){
			//如果没有不是第一次启动则根据系统默认用户查询用户信息（主键id）
			List<User> dfuList=userMapper.selectUser(defaultUser);
			if(dfuList!=null && !dfuList.isEmpty()){
				defaultUser=dfuList.get(0);
			}
		}
		
		UserRole ur=new UserRole();
		ur.setDefaultRole(HuayuConstants.BOOLEAN_TRUE);
		//查询默认角色
		List<UserRole> defaultRoleList=userRoleMapper.selectUserRole(ur);
		
		UserRoleRef userRoleRef=new UserRoleRef();
		if(defaultRoleList!=null && !defaultRoleList.isEmpty()){
			for(UserRole dur: defaultRoleList){
				userRoleRef.setUserId(defaultUser.getId());
				userRoleRef.setRoleId(dur.getId());
				
				if(userRoleRefMapper.checkExistsUserRole(userRoleRef)<=0){
					//给默认用户添加默认角色
					userRoleRefMapper.insertUserRoleRef(userRoleRef);
				} 
			}
		}
		
		ur.setDefaultRole(null);
		ur.setRoleName(defaultUser.getInitRoleName());
		//查询系统用户初始化角色
		List<UserRole> initRole=userRoleMapper.selectUserRole(ur);
		if(initRole!=null && !initRole.isEmpty()){
			for(UserRole iur: initRole){
				userRoleRef.setUserId(defaultUser.getId());
				userRoleRef.setRoleId(iur.getId());
				
				if(userRoleRefMapper.checkExistsUserRole(userRoleRef)<=0){
					//给默认用户添加初始化角色
					userRoleRefMapper.insertUserRoleRef(userRoleRef);
				} 
			}
		}
		
		//查询管理员角色
		ur.setRoleName(null);
		ur.setAdminRole(HuayuConstants.BOOLEAN_TRUE);
		//查询系统用户初始化角色
		List<UserRole> adminRole=userRoleMapper.selectUserRole(ur);
		//初始化功能点权限
		PermissionsList permissionsList=initPermissions();
		if(permissionsList!=null && !permissionsList.isEmpty() 
				&& adminRole!=null && !adminRole.isEmpty()){
			RolePermissionsRef rolePermissionsRef=new RolePermissionsRef();
			for(UserRole ar: adminRole){
				//给管理员角色添加所有的权限
				for(Permissions p: permissionsList){
					if(p.getParentKey()!=null && !"".equals(p.getParentKey().trim())){
						rolePermissionsRef.setRoleId(ar.getId());
						rolePermissionsRef.setPermissionsKey(p.getPermissionsKey());
						
						if(rolePermissionsRefMapper.checkExistsRolePermissionsRef(rolePermissionsRef)<=0){
							rolePermissionsRefMapper.insertRolePermissionsRef(rolePermissionsRef);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 检查定义的异常Code是否存在重复
	 */
	private void checkExCodeRepetition() throws ApplicationException{
		Class<ApplicationExceptionCode> exCodeClass=ApplicationExceptionCode.class;
		
		List<String> exCodeList=new ArrayList<String>();
		List<String> repetitionExCodeList=new ArrayList<String>();
		
		Field[] fields=exCodeClass.getFields();
		if(fields!=null){
			String fName=null;
			String exCode=null;
			for(Field f: fields){
				fName=f.getName();
				if(fName.endsWith("_EX_CODE")){
					try {
						exCode=f.get(null).toString();
						
						//判断是否存在重复定义的异常Code
						if(exCodeList.contains(exCode)){
							repetitionExCodeList.add(exCode);
						}
						else{
							exCodeList.add(exCode);
						}
					} 
					catch (IllegalArgumentException e) {
						throw new ApplicationException(ApplicationExceptionCode.SYSTEM_EX_CODE, e);
					} 
					catch (IllegalAccessException e) {
						throw new ApplicationException(ApplicationExceptionCode.SYSTEM_EX_CODE, e);
					}
				}
			}
		}
		
		//如果存在重复定义的异常编码则抛出异常s
		if(repetitionExCodeList!=null && !repetitionExCodeList.isEmpty()){
			StringBuilder repetitionExCodeStr=new StringBuilder();
			
			for(String rExCode: repetitionExCodeList){
				repetitionExCodeStr.append(rExCode).append(", ");
			}
			
			throw new ApplicationException(repetitionExCodeStr.toString()+"异常编码定义存在重复.");
		}
	}
	
	/**
	 * 初始化权限店
	 */
	private PermissionsList initPermissions() throws ApplicationException{
		List<String> resourcePackages=permissionsConfiguer.getBasePackage();
		
		//权限点集合
		PermissionsList permissionsList=new PermissionsList();
		//重复的功能权限点
		PermissionsList repetitionPermissionsList=new PermissionsList();
		
		List<Class<?>> resourceClasses=loadClassUtil.loadClass(resourcePackages, PermissionsResource.class);
		//解析定义的权限点
		if(resourceClasses!=null && !resourceClasses.isEmpty()){
			Permissions permissions=null;
			Method[] resourceMethods=null;
			PermissionsResource mpaResource=null;
			String parentKey=null;//父权限点key
			String classUrl=null,methodUrl;
			for(Class<?> rc: resourceClasses){
				//获取父资源定义
				mpaResource=rc.getAnnotation(PermissionsResource.class);
				if(mpaResource!=null){
					//获取资源类的url
					RequestMapping classRM=rc.getAnnotation(RequestMapping.class);
					classUrl=classRM.value()[0];
					
					permissions=new Permissions();
					permissions.setPermissionsKey(parentKey=mpaResource.key());
					permissions.setPermissionsDesc(mpaResource.desc());
					
					//如果存在重复的权限点则记录
					if(permissionsList.contains(permissions)){
						repetitionPermissionsList.add(permissions);
					}
					//添加权限到List
					permissionsList.add(permissions);
					
					resourceMethods=rc.getMethods();
					if(resourceMethods!=null){
						for(Method resourceMethod: resourceMethods){
							mpaResource=resourceMethod.getAnnotation(PermissionsResource.class);
							if(mpaResource!=null){
								//获取资源方法url
								RequestMapping methodRM=resourceMethod.getAnnotation(RequestMapping.class);
								methodUrl=methodRM.value()[0];
								
								permissions=new Permissions();
								permissions.setParentKey(parentKey);
								permissions.setPermissionsKey(mpaResource.key());
								permissions.setPermissionsDesc(mpaResource.desc());
								permissions.setPermissionUrl(classUrl+methodUrl);
								
								//如果存在重复的权限点则记录
								if(permissionsList.contains(permissions)){
									repetitionPermissionsList.add(permissions);
								}
								
								//添加权限到List
								permissionsList.add(permissions);
							}
						}
					}
				}
			}
		}
		
		//检验定义的权限点是否存在重复
		if(repetitionPermissionsList!=null && !repetitionPermissionsList.isEmpty()){
			throw new ApplicationException(repetitionPermissionsList.toString()+"权限点定义存在重复.");
		}
		
		//将权限点初始化到数据库
		if(permissionsList!=null && !permissionsList.isEmpty()){
			for(Permissions p: permissionsList){
				//如果数据库中不存在指定的权限点则新增到数据库
				if(permissionsMapper.checkExistsPermission(p)<=0){
					permissionsMapper.insertPermission(p);
				}
			}
		}
		
		return permissionsList;
	}
}
