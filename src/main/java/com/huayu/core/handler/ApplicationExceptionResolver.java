package com.huayu.core.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;

/**
 * 公共异常处理类
 * 
 * @author lenovo20
 * 
 */
public class ApplicationExceptionResolver extends
		SimpleMappingExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object paramObject,
			Exception exception) {
		String contextPath=request.getContextPath();
		//设置项目跟路径
		request.setAttribute(HuayuConstants.CONTEXTPATH_KEY, contextPath);
		ApplicationException applicationException=null;
		
		//记录出现错误次数到session
		HttpSession session=request.getSession();
		int ofc=0;
		Object ofcObj=session.getAttribute(HuayuConstants.OPERATION_FAILED_COUNT);
		if(ofcObj!=null){
			ofc=Integer.parseInt(ofcObj.toString());
		}
		//将错误次数加1
		session.setAttribute(HuayuConstants.OPERATION_FAILED_COUNT, ++ofc);
		
		boolean ajaxFlag=false;
		String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equals("XMLHttpRequest")) {
        	ajaxFlag=true;
        }
		
		if (exception != null) {
			if (!(exception instanceof ApplicationException)) {
				//将异常转化为 ApplicationException
				exception = new ApplicationException(ApplicationExceptionCode.SYSTEM_EX_CODE, exception);
			}

			applicationException = (ApplicationException) exception;
		}
		
		//记录操作日志（异常操作类型），异步记录
		
		if(ajaxFlag){
			//如果为ajax请求则返回ajax指定的页面
			DataResponse dataResponse = new DataResponse();
			dataResponse.setApplicationException(applicationException);
			//设置操作失败的次数
			dataResponse.setOfc(ofc);
			//dataResponse.writeToResponse(response);
			
			ModelAndView mv = new ModelAndView("error/ajaxError.jsp");
			mv.addObject("errorJson_", dataResponse.toString());
			
			return mv;
		}
		
		if(applicationException!=null){
			if(ApplicationExceptionCode.NO_LOGIN_OR_TIME_OUT_EX_CODE.equals(applicationException.getExCode())){
				//登录超时的异常则跳转到登录页面
				ModelAndView mv = new ModelAndView("user/login.jsp");
				mv.addObject(HuayuConstants.REDIRECTURL_KEY, request.getRequestURL());
				
				return mv;
			}
		}
		
		return super.resolveException(request, response, paramObject, applicationException);
	}

}
