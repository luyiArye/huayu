package com.huayu.core.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户角色关系
 * @author Administrator
 *
 */
public class UserRoleRef extends BaseVO{
	private static final long serialVersionUID = -5040714640549282675L;
	
	private BigDecimal userId;
	private BigDecimal roleId;
	private Date expiryDate;
	private Date createdDate;
	private BigDecimal createdBy;
	private Date lastupdatedDate;
	private BigDecimal lastupdatedBy;
	
	public BigDecimal getUserId() {
		return userId;
	}
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
	public BigDecimal getRoleId() {
		return roleId;
	}
	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
