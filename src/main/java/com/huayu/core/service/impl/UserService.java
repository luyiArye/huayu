package com.huayu.core.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.PermissionsList;
import com.huayu.core.bean.vo.User;
import com.huayu.core.bean.vo.UserHeadIco;
import com.huayu.core.bean.vo.UserRole;
import com.huayu.core.bean.vo.UserRoleRef;
import com.huayu.core.config.SystemConfiguer;
import com.huayu.core.dao.mapper.PermissionsMapper;
import com.huayu.core.dao.mapper.UserMapper;
import com.huayu.core.dao.mapper.UserRoleMapper;
import com.huayu.core.dao.mapper.UserRoleRefMapper;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.core.security.IAsymmetric;
import com.huayu.core.service.IUserService;
import com.huayu.core.util.CommonUtil;

@Service
public class UserService extends BaseService implements IUserService {
	private static String SEPARATOR=File.separator;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserRoleRefMapper userRoleRefMapper;
	@Autowired
	private PermissionsMapper permissionsMapper;
	@Resource(name="md5")
	private IAsymmetric md5;
	
	/**
	 * 检查是否存在指定用户
	 * @param user
	 * @return
	 */
	public boolean checkExistsUser(User user) throws ApplicationException{
		return userMapper.checkExistsUser(user)>0;
	}
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user) throws ApplicationException{
		if(userMapper.checkExistsUser(user)>0){
			//检查邮箱是否已经注册
			throw new ApplicationException(ApplicationExceptionCode.USER_ACCOUNT_WAS_EXISTS_EX_CODE);
		}
		
		if(user.getUserName()==null){
			user.setUserName(user.getUserAccount().replace("@", "_"));
		}
		
		//记录加密前的密码
		String oldPassword=user.getUserPassword();
		//密码使用MD5非对称加密
		user.setUserPassword(md5.encrypt(user.getUserPassword()));
		int result=userMapper.insertUser(user);
		
		UserRole ur=new UserRole();
		ur.setDefaultRole(HuayuConstants.BOOLEAN_TRUE);
		//查询默认角色
		List<UserRole> defaultRoleList=userRoleMapper.selectUserRole(ur);
		UserRoleRef userRoleRef=new UserRoleRef();
		if(defaultRoleList!=null && !defaultRoleList.isEmpty()){
			for(UserRole dur: defaultRoleList){
				userRoleRef.setUserId(user.getId());
				userRoleRef.setRoleId(dur.getId());
				
				if(userRoleRefMapper.checkExistsUserRole(userRoleRef)<=0){
					//给注册的用户添加默认角色
					userRoleRefMapper.insertUserRoleRef(userRoleRef);
				} 
			}
		}
		
		//新增完用户再将密码设置为原始密码
		user.setUserPassword(oldPassword);
		
		//创建用户目录
		//用户作品目录
		CommonUtil.checkOrCreateFolder(SystemConfiguer.usersBaseLoacation+SEPARATOR+user.getId()+SEPARATOR+HuayuConstants.USER_PRODUCTION_FOLDER);
		//用户头像目录
		CommonUtil.checkOrCreateFolder(SystemConfiguer.usersBaseLoacation+SEPARATOR+user.getId()+SEPARATOR+HuayuConstants.USER_HEAD_ICO_FOLDER);
		
		return result;
	}
	
	/**
	 * 查询用户信息
	 * @param user
	 * @return
	 */
	public List<User> selectUser(User user) throws ApplicationException{
		return userMapper.selectUser(user);
	}
	
	/**
	 * 登录验证
	 * @return
	 * @throws ApplicationException
	 */
	public User checkLogin(User user) throws ApplicationException{
		List<User> userList=selectUser(user);
		
		User u=null;
		
		if(userList!=null && !userList.isEmpty()){
			u=userList.get(0);
			//如果录入的密码和数据库保存的不一致
			if(!u.getUserPassword().equals(md5.encrypt(user.getUserPassword()))){
				throw new ApplicationException(ApplicationExceptionCode.USER_PASSWORD_ERROR_EX_CODE);
			}
			
			BigDecimal userId=u.getId();
			//查询用户拥有的角色
			List<UserRole> userRoleList=userRoleMapper.selectUserRoleList(userId);
			u.setUserRoleList(userRoleList);
			
			//用户拥有的权限列表
			PermissionsList userPermissions=permissionsMapper.selectUserPermissions(userRoleList);
			//用户被限制的权限列表
			PermissionsList userLimitPermissions=permissionsMapper.selectUserLimitPermissions(userId);
			u.setPermissions(userPermissions);
			u.setLimitPermissions(userLimitPermissions);
		}
		else{
			throw new ApplicationException(ApplicationExceptionCode.USER_ACCOUNT_NOT_FOUND_EX_CODE);
		}
		
		return u;
	}
	
	/**
	 * 查询用户头像
	 * @param userHeadIco
	 * @return
	 */
	public UserHeadIco selectHeadIco(UserHeadIco userHeadIco) throws ApplicationException{
		return userMapper.selectHeadIco(userHeadIco);
	}
	
	/**
	 * 根据id查询用户信息
	 * @param user
	 * @return
	 */
	public User selectUserById(User user) throws ApplicationException{
		List<User> userList=userMapper.selectUser(user);
		if(userList==null || userList.isEmpty()){
			throw new ApplicationException(ApplicationExceptionCode.CAN_NOT_FOUND_USER_EX_CODE);
		}
		
		User u=userList.get(0);
		
		BigDecimal userId=u.getId();
		//查询用户拥有的角色
		List<UserRole> userRoleList=userRoleMapper.selectUserRoleList(userId);
		u.setUserRoleList(userRoleList);
		
		return u;
	}
}
