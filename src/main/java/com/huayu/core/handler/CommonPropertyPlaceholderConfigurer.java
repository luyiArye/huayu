package com.huayu.core.handler;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.huayu.core.config.SystemConfiguer;
import com.huayu.core.exception.ApplicationExceptionCode;

public class CommonPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		
		//邮件服务器相关配置
		/** 发送邮件的服务器 */
		SystemConfiguer.MAIL_SERVER_HOST=props.getProperty("mail.server.host");
		/** 邮件服务器端口 */
		SystemConfiguer.MAIL_SERVER_PORT=props.getProperty("mail.server.port");
		/** 邮件发送人 */
		SystemConfiguer.MAIL_FROM_ADDRESS=props.getProperty("mail.from.address");
		/** 发送人用户名 */
		SystemConfiguer.MAIL_USER_NAME=props.getProperty("mail.user.name");
		/** 邮件发送人密码 */
		SystemConfiguer.MAIL_USER_PASSWORD=props.getProperty("mail.user.password");
		
		//初始化异常信息
		for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            if(keyStr.startsWith("ex.")){
            	ApplicationExceptionCode.EX_MAP.put(keyStr.replace("ex.", ""), value);
            }
        }
	}
}
