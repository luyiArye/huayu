package com.huayu.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.bean.vo.UserRole;
import com.huayu.core.dao.mapper.UserRoleMapper;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.service.IUserRoleService;

@Service
public class UserRoleService extends BaseService implements IUserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	/**
	 * 检查是否存在指定角色
	 * @param roleName
	 * @return
	 * @throws ApplicationException
	 */
	public boolean checkExistsRole(UserRole userRole) throws ApplicationException {
		return userRoleMapper.checkExistsRole(userRole)>0;
	}

	/**
	 * 创建角色
	 * @param userRole
	 * @return
	 * @throws ApplicationException
	 */
	public int insertRole(UserRole userRole) throws ApplicationException {
		return userRoleMapper.insertRole(userRole);
	}
	
	/**
	 * 查询角色
	 * @param userRole
	 * @return
	 */
	public List<UserRole> selectUserRole(UserRole userRole) throws ApplicationException {
		return userRoleMapper.selectUserRole(userRole);
	}
}
