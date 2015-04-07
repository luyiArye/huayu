package com.huayu.core.interceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContext;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.User;

public abstract class BaseInterceptor implements HandlerInterceptor {
	protected Logger LOG=Logger.getLogger(this.getClass());
	
	/**
	 * 获取国际化资源信息
	 * @param code
	 * @return
	 */
	protected String getLocalMessage(String code, HttpServletRequest request){
		RequestContext requestContext = new RequestContext(request);
		return requestContext.getMessage(code);
	}
	
	/**
	 * 获取客户端IP地址
	 * 
	 * @return
	 */
	protected String getClientIP(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	
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
