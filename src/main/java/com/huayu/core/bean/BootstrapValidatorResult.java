package com.huayu.core.bean;

import com.huayu.core.bean.vo.BaseVO;

/**
 * Bootstrap校验ajax返回结果
 * @author Administrator
 *
 */
public class BootstrapValidatorResult extends BaseVO{
	private static final long serialVersionUID = 6018357645828152967L;

	private boolean valid;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
