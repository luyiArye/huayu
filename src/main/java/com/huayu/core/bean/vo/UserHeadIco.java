package com.huayu.core.bean.vo;

import java.math.BigDecimal;

public class UserHeadIco extends BaseVO{
	private static final long serialVersionUID = 1978334006850792965L;
	
	private BigDecimal userId;
	private BigDecimal resourceId;
	//头像类型 1：小头像  2：原始头像
	private BigDecimal headIcoType;
	public BigDecimal getUserId() {
		return userId;
	}
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
	public BigDecimal getResourceId() {
		return resourceId;
	}
	public void setResourceId(BigDecimal resourceId) {
		this.resourceId = resourceId;
	}
	public BigDecimal getHeadIcoType() {
		return headIcoType;
	}
	public void setHeadIcoType(BigDecimal headIcoType) {
		this.headIcoType = headIcoType;
	}
}
