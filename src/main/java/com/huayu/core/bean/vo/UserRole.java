package com.huayu.core.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

public class UserRole extends BaseVO{
	private static final long serialVersionUID = 8661257806741651306L;
	
	private BigDecimal id;
	private String roleName;
	private String roleDesc;
	private BigDecimal adminRole;
	private BigDecimal defaultRole;
	private Date createdDate;
	private BigDecimal createdBy;
	private Date lastupdatedDate;
	private BigDecimal lastupdatedBy;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public BigDecimal getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(BigDecimal adminRole) {
		this.adminRole = adminRole;
	}
	public BigDecimal getDefaultRole() {
		return defaultRole;
	}
	public void setDefaultRole(BigDecimal defaultRole) {
		this.defaultRole = defaultRole;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public BigDecimal getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}
	public Date getLastupdatedDate() {
		return lastupdatedDate;
	}
	public void setLastupdatedDate(Date lastupdatedDate) {
		this.lastupdatedDate = lastupdatedDate;
	}
	public BigDecimal getLastupdatedBy() {
		return lastupdatedBy;
	}
	public void setLastupdatedBy(BigDecimal lastupdatedBy) {
		this.lastupdatedBy = lastupdatedBy;
	}
}
