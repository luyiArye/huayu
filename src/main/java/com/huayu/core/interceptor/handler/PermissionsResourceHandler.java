package com.huayu.core.interceptor.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.annotation.PermissionsResource;
import com.huayu.core.bean.vo.PermissionsList;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;

public class PermissionsResourceHandler extends HandlerBase{
	/**
	 * 拦截处理
	 * @author arye.luyi Administrator
	 * @date 2015-3-16 下午3:39:17
	 * @param request
	 * @param response
	 * @param obj
	 * @param paramModelAndView
	 * @throws ApplicationException
	 */
	public void handler(HttpServletRequest request,
			HttpServletResponse response, Object obj,
			ModelAndView paramModelAndView) throws ApplicationException{
		HandlerMethod handlerMethod = (HandlerMethod) obj;
		HttpSession session=request.getSession();
		
		//获取方法是否需要登录检查
		boolean loginCheck=handlerMethod.getMethodAnnotation(NoLoginRequired.class)==null;
		//获取登录用户信息
		User loginUser=getLoginUser(session);
		
		//获取方法权限信息
		PermissionsResource permissionsResource=handlerMethod.getMethodAnnotation(PermissionsResource.class);
		//需要登录并且方法存在权限信息，拦截权限
		if(loginCheck && permissionsResource!=null){
			//方法权限标识
			String resourceKey=permissionsResource.key();
			
			//获取用户所有的权限列表
			PermissionsList permissionsList=loginUser.getPermissions();
			if(permissionsList==null || permissionsList.isEmpty() || !permissionsList.hasPermissions(resourceKey)){
				//权限不足
				throw new ApplicationException(ApplicationExceptionCode.NO_PERMISSIONS_EX_CODE);
			}
			
			//获取用户被限制的权限列表
			PermissionsList limitPermissionsList=loginUser.getLimitPermissions();
			if(limitPermissionsList!=null && limitPermissionsList.hasPermissions(resourceKey)){
				//如果用户权限被限制则抛出异常
				throw new ApplicationException(ApplicationExceptionCode.PERMISSIONS_LIMIT_EX_CODE);
			}
		}
	}
}
