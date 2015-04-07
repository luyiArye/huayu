package com.huayu.core.mail;

import java.io.IOException;

import org.springframework.stereotype.Component;

import freemarker.template.TemplateException;

@Component
public class TemplateMailSender extends SimpleMailSender{
	/**
	 * 以文本格式发送邮件
	 */
	public void sendTextMail(MailInfo mailInfo) {
		try {
			String templateContent=FreeMarkTools.getResult(mailInfo.getTemplate(), mailInfo.getParam());
			mailInfo.setContent(templateContent);
			
			super.sendTextMail(mailInfo);
		} 
		catch (TemplateException e) {
			LOG.error(e.getMessage(), e);
		} 
		catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 以HTML格式发送邮件
	 */
	public void sendHtmlMail(MailInfo mailInfo) {
		try {
			String templateContent = FreeMarkTools.getResult(mailInfo.getTemplate(), mailInfo.getParam());
			
			mailInfo.setContent(templateContent);
			
			super.sendHtmlMail(mailInfo);
		} 
		catch (TemplateException e) {
			LOG.error(e.getMessage(), e);
		} 
		catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
