package com.huayu.user.service;

import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;

/**
 * 忘记密码
 * @author Administrator
 *
 */
public interface IForgetPwdService {
	/**
	 * 忘记密码
	 * @throws ApplicationException
	 */
	public void submitForgetPwd(User user, String basePath) throws ApplicationException;
	
	/**
	 * 忘记密码校验码解析用户信息
	 * @param fv
	 * @return
	 * @throws ApplicationException
	 */
	public User resetPwdParseUser(String fv) throws ApplicationException;
	
	/**
	 * 提交重置密码
	 * @param user
	 * @throws ApplicationException
	 */
	public void submitResetPwd(User user) throws ApplicationException;
}
