package com.huayu.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huayu.core.annotation.PermissionsResource;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.config.PermissionsConfiguer;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.util.LoadClassUtil;

@PermissionsResource(key="core.Permissions", desc="权限管理")
@Scope("request")
@Controller
@RequestMapping("/permissions")
public class PermissionsController extends BaseController{
	@Autowired
	private LoadClassUtil loadClassUtil;
	
	@Autowired(required=true)
	private PermissionsConfiguer permissionsConfiguer;
	
	/**
	 * 加载功能点权限列表
	 * @return
	 * @throws ApplicationException
	 */
	@PermissionsResource(key="core.Permissions.refresh", desc="刷新权限")
	@RequestMapping(value="/refresh", method=RequestMethod.GET)
	@ResponseBody
	public DataResponse refresh() throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		List<String> resourcePackages=permissionsConfiguer.getBasePackage();
		
		List<Class<?>> resourceClasses=loadClassUtil.loadClass(resourcePackages, PermissionsResource.class);
		if(resourceClasses!=null && !resourceClasses.isEmpty()){
			for(Class<?> rc: resourceClasses){
				PermissionsResource mpaResource=rc.getAnnotation(PermissionsResource.class);
				String key=mpaResource.key();
				String desc=mpaResource.desc();
				
				rc.getMethods();
				LOG.debug(rc+": "+key+": "+desc);
			}
		}
		
		return dataResponse;
	}
}
