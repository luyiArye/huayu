package com.huayu.core.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class HuayuCommonsMultipartResolver extends CommonsMultipartResolver{
	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request)
			throws MultipartException {
		// TODO Auto-generated method stub
		return super.parseRequest(request);
	}
	
	@Override
	public MultipartHttpServletRequest resolveMultipart(
			HttpServletRequest request) throws MultipartException {
		// TODO Auto-generated method stub
		return super.resolveMultipart(request);
	}
}
