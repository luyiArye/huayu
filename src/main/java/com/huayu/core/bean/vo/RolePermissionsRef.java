package com.huayu.core.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

public class RolePermissionsRef extends BaseVO {
	private static final long serialVersionUID = 8066984269404961168L;

	private BigDecimal roleId;
	private String permissionsKey;
	private Date createdDate;
	private BigDecimal createdBy;
	private Date lastupdatedDate;
	private BigDecimal lastupdatedBy;
	
	public BigDecimal getRoleId() {
		return roleId;
	}
	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}
	public String getPermissionsKey() {
		return permissionsKey;
	}
	public void setPermissionsKey(String permissionsKey) {
		this.permissionsKey = permissionsKey;
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
