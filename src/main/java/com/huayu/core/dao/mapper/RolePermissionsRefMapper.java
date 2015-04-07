package com.huayu.core.dao.mapper;

import com.huayu.core.bean.vo.RolePermissionsRef;

public interface RolePermissionsRefMapper {
	/**
	 * 新增角色对应的权限
	 * @param rolePermissionsRef
	 * @return
	 */
	public int insertRolePermissionsRef(RolePermissionsRef rolePermissionsRef);
	
	/**
	 * 检查角色是否已经存在指定的权限
	 * @param rolePermissionsRef
	 * @return
	 */
	public long checkExistsRolePermissionsRef(RolePermissionsRef rolePermissionsRef);
}
