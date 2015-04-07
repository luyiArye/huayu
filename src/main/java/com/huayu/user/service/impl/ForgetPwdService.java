package com.huayu.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.User;
import com.huayu.core.dao.mapper.UserMapper;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.core.mail.MailInfo;
import com.huayu.core.mail.TemplateMailSender;
import com.huayu.core.security.BASE64;
import com.huayu.core.security.IAsymmetric;
import com.huayu.user.bean.ForgetPwdVerifyInfo;
import com.huayu.user.dao.mapper.ForgetPwdVerifyInfoMapper;
import com.huayu.user.service.IForgetPwdService;

/**
 * 忘记密码
 * @author Administrator
 *
 */
@Service
public class ForgetPwdService implements IForgetPwdService {
	@Autowired
	private TemplateMailSender templateMailSender;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ForgetPwdVerifyInfoMapper forgetPwdVerifyInfoMapper;
	
	@Resource(name="base64")
	private BASE64 base64;
	@Resource(name="md5")
	private IAsymmetric md5;
	
	/**
	 * 使用base64加密申请忘记密码字符串
	 * @param str
	 * @return
	 */
	private String encodeFPString(String str){
		str=base64.encrypt(str);
		str=base64.encrypt(str);
		str=base64.encrypt(str);
		str=base64.encrypt(str);
		str=base64.encrypt(str);
		return str;
	}
	
	/**
	 * 使用base64解密申请忘记密码字符串
	 * @param str
	 * @return
	 */
	private String decodeFPString(String str){
		str=base64.decrypt(str);
		str=base64.decrypt(str);
		str=base64.decrypt(str);
		str=base64.decrypt(str);
		str=base64.decrypt(str);
		return str;
	}
	
	/**
	 * 忘记密码
	 * @throws ApplicationException
	 */
	public void submitForgetPwd(User user, String basePath) throws ApplicationException{
		if(userMapper.checkExistsUser(user)<=0){
			//未找到用户信息
			throw new ApplicationException(ApplicationExceptionCode.CAN_NOT_FOUND_USER_EX_CODE);
		}
		
		String forgetTime=String.valueOf(new Date().getTime());
		//忘记密码字符串 注册邮箱;申请时间
		StringBuffer forgetPwdBuffer=new StringBuffer();
		forgetPwdBuffer.append(user.getUserAccount())
		.append(";")
		.append(forgetTime);
		
		//忘记密码url
		StringBuffer forgetPwdUrl=new StringBuffer();
		forgetPwdUrl.append(basePath)
		.append("forgetPwd/resetPwd?fv=")
		//使用base64加密忘记密码校验字符串
		.append(encodeFPString(forgetPwdBuffer.toString()));
		
		MailInfo mailInfo=new MailInfo();
		mailInfo.setToAddress(user.getUserAccount());
		mailInfo.setSubject("忘记密码验证！");
		mailInfo.setTemplate("forgetPwd.ftl");
		Map<String, Object> mailParam=new HashMap<String , Object>();
		mailParam.put("user", user);
		mailParam.put("forgetPwdUrl", forgetPwdUrl.toString());
		mailInfo.setParam(mailParam);
		templateMailSender.sendHtmlMail(mailInfo);
		
		ForgetPwdVerifyInfo forgetPwdVerifyInfo=new ForgetPwdVerifyInfo();
		forgetPwdVerifyInfo.setUserAccount(user.getUserAccount());
		forgetPwdVerifyInfo.setForgetTime(forgetTime);
		forgetPwdVerifyInfoMapper.insertForgetPwdVerifyInfo(forgetPwdVerifyInfo);
	}
	
	/**
	 * 忘记密码校验码解析用户信息
	 * @param fv
	 * @return
	 * @throws ApplicationException
	 */
	public User resetPwdParseUser(String fv) throws ApplicationException{
		if(fv==null || "".equals(fv.trim())){
			//没有找到校验字符串
			throw new ApplicationException(ApplicationExceptionCode.FORGET_PWD_VF_ERROR_OR_NOT_FOUND_EX_CODE);
		}
		
		//使用base64解密忘记密码校验字符串
		fv=decodeFPString(fv);
		
		String[] fvArr=fv.split(";");
		if(fvArr==null || fvArr.length!=2){
			//校验字符串错误
			throw new ApplicationException(ApplicationExceptionCode.FORGET_PWD_VF_ERROR_OR_NOT_FOUND_EX_CODE);
		}
		
		//获取提交忘记密码的时间
		String forgetTime=null;
		try{
			forgetTime=fvArr[1];
			long sysTime=new Date().getTime();
			if(sysTime-Long.valueOf(forgetTime)>HuayuConstants.ONE_DAY_MILLISECOND){
				//链接有效期为一天
				throw new ApplicationException(ApplicationExceptionCode.FORGET_PWD_VF_TIMEOUT_EX_CODE);
			}
		}
		catch(Exception e){
			//校验字符串错误
			throw new ApplicationException(ApplicationExceptionCode.FORGET_PWD_VF_ERROR_OR_NOT_FOUND_EX_CODE, e);
		}
		
		//获取校验信息中的用户名
		String userAccount=fvArr[0];
		User user=new User();
		user.setUserAccount(userAccount);
		//查询用户信息
		List<User> userList=userMapper.selectUser(user);
		
		if(userList==null || userList.isEmpty()){
			//校验字符串错误
			throw new ApplicationException(ApplicationExceptionCode.FORGET_PWD_VF_ERROR_OR_NOT_FOUND_EX_CODE);
		}
		
		ForgetPwdVerifyInfo forgetPwdVerifyInfo=new ForgetPwdVerifyInfo();
		forgetPwdVerifyInfo.setUserAccount(userAccount);
		if(forgetPwdVerifyInfoMapper.checkForgetPwdVerifyInfo(forgetPwdVerifyInfo)<=0){
			//校验信息已经使用
			throw new ApplicationException(ApplicationExceptionCode.FORGET_PWD_VF_ALREADY_USED_EX_CODE);
		}
		
		return userList.get(0);
	}
	
	/**
	 * 提交重置密码
	 * @param user
	 * @throws ApplicationException
	 */
	public void submitResetPwd(User user) throws ApplicationException{
		if(userMapper.checkExistsUser(user)<=0){
			//未找到用户信息
			throw new ApplicationException(ApplicationExceptionCode.CAN_NOT_FOUND_USER_EX_CODE);
		}
		
		//密码使用MD5加密
		user.setUserPassword(md5.encrypt(user.getUserPassword()));
		userMapper.updateUser(user);
		
		ForgetPwdVerifyInfo forgetPwdVerifyInfo=new ForgetPwdVerifyInfo();
		forgetPwdVerifyInfo.setUserAccount(user.getUserAccount());
		forgetPwdVerifyInfoMapper.disableForgetPwdVerifyInfo(forgetPwdVerifyInfo);
	}
}
