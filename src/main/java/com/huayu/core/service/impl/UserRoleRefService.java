package com.huayu.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.bean.vo.UserRoleRef;
import com.huayu.core.dao.mapper.UserRoleRefMapper;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.service.IUserRoleRefService;

@Service
public class UserRoleRefService extends BaseService implements IUserRoleRefService {
	@Autowired
	private UserRoleRefMapper userRoleRefMapper;
	
	/**
	 * 新增用户角色关系
	 * @param userRoleRef
	 * @return
	 */
	public int insertUserRoleRef(UserRoleRef userRoleRef) throws ApplicationException{
		return userRoleRefMapper.insertUserRoleRef(userRoleRef);
	}
}
