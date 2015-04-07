package com.huayu.core.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 画语资源
 * @author Administrator
 *
 */
public class HuayuResources extends BaseVO{
	private static final long serialVersionUID = -6212682069686460851L;
	
	private BigDecimal id;
	private String resourceName;
	private String resourcePath;
	private String resourceType;
	private String contentType;
	private BigDecimal resourceSize;
	private BigDecimal resourceWidth;
	private BigDecimal resourceHeight;
	private BigDecimal status;
	private BigDecimal createdBy;
	private Date createdDate;
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	@JsonIgnore
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public BigDecimal getResourceSize() {
		return resourceSize;
	}
	public void setResourceSize(BigDecimal resourceSize) {
		this.resourceSize = resourceSize;
	}
	public BigDecimal getResourceWidth() {
		return resourceWidth;
	}
	public void setResourceWidth(BigDecimal resourceWidth) {
		this.resourceWidth = resourceWidth;
	}
	public BigDecimal getResourceHeight() {
		return resourceHeight;
	}
	public void setResourceHeight(BigDecimal resourceHeight) {
		this.resourceHeight = resourceHeight;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
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
