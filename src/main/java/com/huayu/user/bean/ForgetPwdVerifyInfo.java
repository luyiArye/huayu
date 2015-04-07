package com.huayu.user.bean;

import java.math.BigDecimal;

/**
 * 用户忘记密码核实信息
 * @author Administrator
 *
 */
public class ForgetPwdVerifyInfo {
	private String userAccount;
	private String forgetTime;
	private BigDecimal status;
	
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getForgetTime() {
		return forgetTime;
	}
	public void setForgetTime(String forgetTime) {
		this.forgetTime = forgetTime;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
	}
}
