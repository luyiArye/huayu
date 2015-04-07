package com.huayu.core.service;

import java.util.List;

import com.huayu.core.bean.vo.UserRole;
import com.huayu.core.exception.ApplicationException;

/**
 * 角色
 * @author Administrator
 *
 */
public interface IUserRoleService {
	/**
	 * 检查是否存在指定角色
	 * @param roleName
	 * @return
	 * @throws ApplicationException
	 */
	public boolean checkExistsRole(UserRole userRole) throws ApplicationException;
	
	/**
	 * 创建角色
	 * @param userRole
	 * @return
	 * @throws ApplicationException
	 */
	public int insertRole(UserRole userRole) throws ApplicationException;
	
	/**
	 * 查询角色
	 * @param userRole
	 * @return
	 */
	public List<UserRole> selectUserRole(UserRole userRole) throws ApplicationException;
}
