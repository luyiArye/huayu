package com.huayu.core.interceptor.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;

public abstract class HandlerBase {
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
	abstract public void handler(HttpServletRequest request,
			HttpServletResponse response, Object obj,
			ModelAndView paramModelAndView) throws ApplicationException;
	
	/**
	 * 获取登录用户信息
	 * @return
	 */
	protected User getLoginUser(HttpSession session){
		User u=null;
		
		Object userObj=session.getAttribute(HuayuConstants.LOGIN_USER_SESSION_KEY);
		if(userObj!=null){
			u=(User)userObj;
		}
		
		return u;
	}
}
