package com.huayu.core.interceptor;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.huayu.core.HuayuConstants;
import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.core.interceptor.handler.HandlerBase;
import com.huayu.core.interceptor.handler.HandlerFactory;

/**
 * 公共拦截器
 * @author Administrator
 *
 */
public class CommonInterceptor extends BaseInterceptor {
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object paramObject,
			Exception exception) throws Exception {
		//正常操作结束时，记录正常操作日志
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj,
			ModelAndView paramModelAndView) throws Exception {
		String contextPath=request.getContextPath();
		LOG.debug("contextPath:"+contextPath);
		//设置项目跟路径
		request.setAttribute(HuayuConstants.CONTEXTPATH_KEY, contextPath);
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj)
			throws Exception {
		if (obj instanceof ResourceHttpRequestHandler 
				|| obj instanceof DefaultServletHttpRequestHandler) {
			// 如果是访问的静态资源不拦截
			return true;
		}

		LOG.debug("request url: "+request.getRequestURL());
		HttpSession session=request.getSession();
		HandlerMethod handlerMethod = (HandlerMethod) obj;
		//获取方法是否需要登录检查
		boolean loginCheck=handlerMethod.getMethodAnnotation(NoLoginRequired.class)==null;
		
		//登录用户信息
		User loginUser=getLoginUser(session);
		//需要登录检查，拦截session
		if(loginCheck){
			if(loginUser==null){
				//未登录、登录超时
				throw new ApplicationException(ApplicationExceptionCode.NO_LOGIN_OR_TIME_OUT_EX_CODE);
			}
		}
		
		//获取方法上的注解
		Annotation[] annotations=handlerMethod.getMethod().getAnnotations();
		if(annotations!=null){
			for(Annotation a: annotations){
				HandlerBase interceptorHandler=HandlerFactory.getHandler(a);
				if(interceptorHandler!=null){
					//执行拦截处理
					interceptorHandler.handler(request, response, obj, null);
				}
			}
		}
		
		return true;
	}
}
