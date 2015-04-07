package com.huayu.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.huayu.core.bean.gzip.GZipResponse;

public class GZipInterceptor extends BaseInterceptor {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (isGZipEncoding(request)) {
			GZipResponse zipResponse = new GZipResponse(response);
			response.setHeader("Content-Encoding", "gzip");
			zipResponse.flush();
		}
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 判断浏览器是否支持GZIP
	 * 
	 * @param request
	 * @return
	 */
	private static boolean isGZipEncoding(HttpServletRequest request) {
		boolean flag = false;
		String encoding = request.getHeader("Accept-Encoding");
		if (encoding.indexOf("gzip") != -1) {
			flag = true;
		}
		return flag;
	}
}
