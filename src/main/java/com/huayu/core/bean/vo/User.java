package com.huayu.core.bean.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class User extends BaseVO{
	private static final long serialVersionUID = 6592272318207506650L;
	
	//主键id
	private BigDecimal id;
	//账户
	private String userAccount;
	//用户名称（昵称）
	private String userName;
	//密码
	private String userPassword;
	//重复密码，注册时使用
	private String userPasswordAgain;
	//用户描述
	private String userDesc;
	//用户状态
	private BigDecimal userStatus;
	//系统默认用户标识 1：系统内部用户    0：普通用户
	private BigDecimal systemUser; 
	private Date createdDate;
	private BigDecimal createdBy;
	private Date lastupdatedDate;
	private BigDecimal lastupdatedBy;
	//账户类型 1：邮箱账户
	private BigDecimal accountType;
	//用户签名照资源id
	private BigDecimal signatureImgId;
	
	//初始化角色名称
	private String initRoleName;
	
	//用户存在的所有的权限key列表
	private PermissionsList permissions;
	//用户被限制的权限key列表
	private PermissionsList limitPermissions;
	//用户角色
	private List<UserRole> userRoleList;

	public BigDecimal getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(BigDecimal userStatus) {
		this.userStatus = userStatus;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	@JsonIgnore
	public String getUserPasswordAgain() {
		return userPasswordAgain;
	}

	public void setUserPasswordAgain(String userPasswordAgain) {
		this.userPasswordAgain = userPasswordAgain;
	}

	public BigDecimal getSignatureImgId() {
		return signatureImgId;
	}

	public void setSignatureImgId(BigDecimal signatureImgId) {
		this.signatureImgId = signatureImgId;
	}

	public BigDecimal getAccountType() {
		return accountType;
	}

	public void setAccountType(BigDecimal accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public BigDecimal getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(BigDecimal systemUser) {
		this.systemUser = systemUser;
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

	public String getInitRoleName() {
		return initRoleName;
	}

	public void setInitRoleName(String initRoleName) {
		this.initRoleName = initRoleName;
	}

	public PermissionsList getPermissions() {
		return permissions;
	}

	public void setPermissions(PermissionsList permissions) {
		this.permissions = permissions;
	}

	public PermissionsList getLimitPermissions() {
		return limitPermissions;
	}

	public void setLimitPermissions(PermissionsList limitPermissions) {
		this.limitPermissions = limitPermissions;
	}
}
