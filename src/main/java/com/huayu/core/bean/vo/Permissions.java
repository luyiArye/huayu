package com.huayu.core.bean.vo;

public class Permissions extends BaseVO {
	private static final long serialVersionUID = 5311734499771683771L;

	private String permissionsKey;
	private String parentKey;
	private String permissionsDesc;
	private String permissionUrl;
	
	public String getPermissionUrl() {
		return permissionUrl;
	}
	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}
	public String getPermissionsKey() {
		return permissionsKey;
	}
	public void setPermissionsKey(String permissionsKey) {
		this.permissionsKey = permissionsKey;
	}
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getPermissionsDesc() {
		return permissionsDesc;
	}
	public void setPermissionsDesc(String permissionsDesc) {
		this.permissionsDesc = permissionsDesc;
	}
}
