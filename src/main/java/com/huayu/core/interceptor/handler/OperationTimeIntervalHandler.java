package com.huayu.core.interceptor.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.huayu.core.annotation.OperationTimeInterval;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;

public class OperationTimeIntervalHandler extends HandlerBase {

	public void handler(HttpServletRequest request,
			HttpServletResponse response, Object obj,
			ModelAndView paramModelAndView) throws ApplicationException {
		HandlerMethod handlerMethod = (HandlerMethod) obj;
		HttpSession session=request.getSession();
		
		String requestURI=request.getRequestURI();
		Object operationTimeObj=session.getAttribute(requestURI);
		if(operationTimeObj==null){
			long sysDateTime=new Date().getTime();
			//记录url对应的操作时间到session中
			session.setAttribute(requestURI, sysDateTime);
			return ;
		}
		
		//获取时间间隔的秒数
		OperationTimeInterval operationTimeInterval=handlerMethod.getMethodAnnotation(OperationTimeInterval.class);
		
		//允许的时间间隔
		int timeInterval=0;
		if(operationTimeInterval!=null){
			timeInterval=operationTimeInterval.value();
		}
		
		//获取session中保存的上次操作的时间
		long prevOperationTime=Long.valueOf(operationTimeObj.toString());
		long sysDateTime=new Date().getTime();
		if(timeInterval>0 && sysDateTime-prevOperationTime<=timeInterval*1000){
			//抛出操作太快异常
			throw new ApplicationException(ApplicationExceptionCode.OPERATION_TOO_FAST_EX_CODE);
		}
		
		//记录url对应的操作时间到session中
		session.setAttribute(requestURI, sysDateTime);
	}

}
