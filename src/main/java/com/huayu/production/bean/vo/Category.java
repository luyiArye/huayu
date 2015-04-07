package com.huayu.production.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.huayu.core.bean.vo.BaseVO;

/**
 * 画语分类
 * @author Administrator
 *
 */
public class Category extends BaseVO{
	private static final long serialVersionUID = -8275848938563777569L;
	
	private BigDecimal id;
	private BigDecimal pid;
	private String categoryName;
	private String categoryDesc;
	private BigDecimal createdBy;
	private Date createdDate;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getPid() {
		return pid;
	}
	public void setPid(BigDecimal pid) {
		this.pid = pid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public BigDecimal getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
