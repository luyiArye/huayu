package com.huayu.core.dao.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huayu.core.bean.vo.Permissions;
import com.huayu.core.bean.vo.PermissionsList;
import com.huayu.core.bean.vo.UserRole;

public interface PermissionsMapper {
	/**
	 * 检查是否存在指定的权限点
	 * @param permissions
	 * @return
	 */
	public long checkExistsPermission(Permissions permissions);
	
	/**
	 * 新增权限点
	 * @param permissions
	 * @return
	 */
	public int insertPermission(Permissions permissions);
	
	/**
	 * 查询用户权限
	 * @param userRoleList
	 * @return
	 */
	public PermissionsList selectUserPermissions(@Param("userRoleList") List<UserRole> userRoleList);
	
	/**
	 * 查询用户被限制的权限
	 * @param userRoleList
	 * @return
	 */
	public PermissionsList selectUserLimitPermissions(BigDecimal userId);
}
