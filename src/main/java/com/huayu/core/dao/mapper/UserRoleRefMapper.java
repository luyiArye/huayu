package com.huayu.core.dao.mapper;

import com.huayu.core.bean.vo.UserRoleRef;

public interface UserRoleRefMapper {
	/**
	 * 新增用户角色关系
	 * @param userRoleRef
	 * @return
	 */
	public int insertUserRoleRef(UserRoleRef userRoleRef);
	
	/**
	 * 检查是否已存在指定权限
	 * @param userRoleRef
	 * @return
	 */
	public long checkExistsUserRole(UserRoleRef userRoleRef);
}
