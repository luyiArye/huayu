package com.huayu.core.interceptor.handler;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.huayu.core.HuayuConstants;
import com.huayu.core.annotation.RequiredVCode;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;

public class RequiredVCodeHandler extends HandlerBase{
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
		
		//session中记录的失败次数
		int ofc=0;
		Object ofcObj=session.getAttribute(HuayuConstants.OPERATION_FAILED_COUNT);
		if(ofcObj!=null){
			ofc=Integer.parseInt(ofcObj.toString());
		}
		
		//获取方法是否需要验证码
		RequiredVCode requiredVCode=handlerMethod.getMethodAnnotation(RequiredVCode.class);
		if(requiredVCode!=null && ofc>=requiredVCode.failedCount()){
			//获取session中保存的验证码
			String sessionVCode=null;
			Object codeObj=session.getAttribute(HuayuConstants.V_CODE_SESSION_KEY);
			if(codeObj==null){
				Producer captchaProducer=getProducer();
				sessionVCode=captchaProducer.createText();
				session.setAttribute(HuayuConstants.V_CODE_SESSION_KEY, sessionVCode);  
			}
			else{
				sessionVCode=codeObj.toString();
			}
			
			//获取请求参数的验证码
			String requestVCode=request.getParameter("vCode_");
			if(!sessionVCode.equalsIgnoreCase(requestVCode)){
				//如果提交的验证码和session中的验证码不一致则抛出异常
				throw new ApplicationException(ApplicationExceptionCode.VCODE_ERROR_EX_CODE);
			}
			
			//一个校验码只能使用一次,使用完后清空
			session.removeAttribute(HuayuConstants.V_CODE_SESSION_KEY);
		}
	}

	/**
	 * 获取验证码生成对象
	 * @return
	 */
	private Producer getProducer(){
		Properties properties=new Properties();
		DefaultKaptcha captchaProducer=new DefaultKaptcha(); 
		Config config=new Config(properties);
		
		captchaProducer.setConfig(config);
		
		return captchaProducer;
	}
}
