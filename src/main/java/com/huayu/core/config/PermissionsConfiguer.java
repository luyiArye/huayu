package com.huayu.core.config;

import java.util.List;

import com.huayu.core.bean.vo.User;
import com.huayu.core.bean.vo.UserRole;

public class PermissionsConfiguer {
	//功能权限的类根包
	private List<String> basePackage;
	//默认角色
	private List<UserRole> defaultRoles;
	//系统默认用户
	private User defaultUser;

	public User getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(User defaultUser) {
		this.defaultUser = defaultUser;
	}

	public List<UserRole> getDefaultRoles() {
		return defaultRoles;
	}

	public void setDefaultRoles(List<UserRole> defaultRoles) {
		this.defaultRoles = defaultRoles;
	}

	public List<String> getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(List<String> basePackage) {
		this.basePackage = basePackage;
	}
}
