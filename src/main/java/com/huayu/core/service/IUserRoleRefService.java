package com.huayu.core.service;

import com.huayu.core.bean.vo.UserRoleRef;
import com.huayu.core.exception.ApplicationException;

/**
 * 用户角色关系
 * @author Administrator
 *
 */
public interface IUserRoleRefService {
	/**
	 * 新增用户角色关系
	 * @param userRoleRef
	 * @return
	 */
	public int insertUserRoleRef(UserRoleRef userRoleRef) throws ApplicationException;
}
