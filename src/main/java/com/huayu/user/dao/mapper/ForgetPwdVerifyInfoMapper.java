package com.huayu.user.dao.mapper;

import com.huayu.user.bean.ForgetPwdVerifyInfo;

/**
 * 用户忘记密码核实信息
 * @author Administrator
 *
 */
public interface ForgetPwdVerifyInfoMapper {
	/**
	 * 新增核实信息
	 * @param forgetPwdVerifyInfo
	 * @return
	 */
	public int insertForgetPwdVerifyInfo(ForgetPwdVerifyInfo forgetPwdVerifyInfo);
	
	/**
	 * 校验审核信息
	 * @param forgetPwdVerifyInfo
	 * @return
	 */
	public int checkForgetPwdVerifyInfo(ForgetPwdVerifyInfo forgetPwdVerifyInfo);
	
	/**
	 * 失效审核信息
	 * @param forgetPwdVerifyInfo
	 * @return
	 */
	public int disableForgetPwdVerifyInfo(ForgetPwdVerifyInfo forgetPwdVerifyInfo);
}
