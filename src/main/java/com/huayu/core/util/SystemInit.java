package com.huayu.core.util;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.huayu.core.service.impl.SystemService;

/**
 * 容器初始化
 * @author Administrator
 *
 */
public class SystemInit implements InitializingBean, ServletContextAware{
	@Autowired
	private SystemService systemService;

	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		//System.out.println(context);
	}

	public void afterPropertiesSet() throws Exception {
		//系统初始化
		systemService.initSystemInfo();
	}  

}
