package com.huayu.production.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.huayu.core.bean.vo.BaseVO;
import com.huayu.core.bean.vo.User;

public class ProductionOperation extends BaseVO{
	private static final long serialVersionUID = -8855658111027652058L;

	private BigDecimal productionId;
	private BigDecimal operationType;
	private BigDecimal userId;
	private Date operationDate;
	private String ipAddress;
	private String operationDesc;
	
	//操作次数
	private int operationCount;
	private User operationUser;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getOperationCount() {
		return operationCount;
	}
	public void setOperationCount(int operationCount) {
		this.operationCount = operationCount;
	}
	public User getOperationUser() {
		return operationUser;
	}
	public void setOperationUser(User operationUser) {
		this.operationUser = operationUser;
	}
	public BigDecimal getProductionId() {
		return productionId;
	}
	public void setProductionId(BigDecimal productionId) {
		this.productionId = productionId;
	}
	public BigDecimal getOperationType() {
		return operationType;
	}
	public void setOperationType(BigDecimal operationType) {
		this.operationType = operationType;
	}
	public BigDecimal getUserId() {
		return userId;
	}
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
}
