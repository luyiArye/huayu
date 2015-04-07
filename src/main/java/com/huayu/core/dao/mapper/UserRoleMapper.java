package com.huayu.core.dao.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.huayu.core.bean.vo.UserRole;
import com.huayu.core.exception.ApplicationException;

public interface UserRoleMapper {
	/**
	 * 检查是否存在指定角色
	 * @param roleName
	 * @return
	 * @throws ApplicationException
	 */
	public long checkExistsRole(UserRole userRole);
	
	/**
	 * 创建角色
	 * @param userRole
	 * @return
	 * @throws ApplicationException
	 */
	public int insertRole(UserRole userRole);
	
	/**
	 * 查询角色
	 * @param userRole
	 * @return
	 */
	public List<UserRole> selectUserRole(UserRole userRole);
	
	/**
	 * 查询用户角色列表
	 * @param userId
	 * @return
	 */
	public List<UserRole> selectUserRoleList(BigDecimal userId);
}
