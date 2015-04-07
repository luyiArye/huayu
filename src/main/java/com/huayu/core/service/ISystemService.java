package com.huayu.core.service;

import com.huayu.core.exception.ApplicationException;

public interface ISystemService {
	/**
	 * 初始化系统信息
	 */
	public void initSystemInfo() throws ApplicationException;
}
