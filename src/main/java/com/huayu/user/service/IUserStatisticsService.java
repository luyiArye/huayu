package com.huayu.user.service;

import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.user.bean.UserStatistics;

public interface IUserStatisticsService {
	/**
	 * 用户相关统计
	 * @param user
	 * @return
	 * @throws ApplicationException
	 */
	public UserStatistics statistics(User user) throws ApplicationException;
}
