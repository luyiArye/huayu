package com.huayu.core.config;

import java.io.File;

import com.huayu.core.exception.ApplicationException;
import com.huayu.core.util.CommonUtil;

public class SystemConfiguer {
	public static String usersBaseLoacation;
	//上传资源跟路径
	public static String resourcesBaseLocation;
	//上传资源临时目录
	public static String resourceTempLocation;
	
	/** 发送邮件的服务器 */
	public static String MAIL_SERVER_HOST="smtp.163.com";
	/** 邮件服务器端口 */
	public static String MAIL_SERVER_PORT="25";
	/** 邮件发送人 */
	public static String MAIL_FROM_ADDRESS;
	/** 发送人用户名 */
	public static String MAIL_USER_NAME;
	/** 邮件发送人密码 */
	public static String MAIL_USER_PASSWORD;

	public void setResourceTempLocation(String resourceTempLocation) {
		SystemConfiguer.resourceTempLocation = resourceTempLocation;
	}

	public void setResourcesBaseLocation(String resourcesBaseLocation) {
		SystemConfiguer.resourcesBaseLocation = resourcesBaseLocation;
		SystemConfiguer.usersBaseLoacation=resourcesBaseLocation+File.separator+"users";
	}
	
	/**
	 * 初始化系统配置
	 * @throws ApplicationException
	 */
	public void init() throws ApplicationException{
		CommonUtil.checkOrCreateFolder(resourcesBaseLocation);
		CommonUtil.checkOrCreateFolder(resourceTempLocation);
		CommonUtil.checkOrCreateFolder(usersBaseLoacation);
	}
}
