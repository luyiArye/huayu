package com.huayu.production.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.huayu.core.bean.vo.BaseVO;
import com.huayu.core.bean.vo.User;

/**
 * 作品评论
 * @author arye.luyi Administrator
 * @date 2015-3-14 上午10:40:45
 */
public class ProductionComment extends BaseVO{
	private static final long serialVersionUID = -7988117131011504572L;

	private BigDecimal id;
	private BigDecimal refId;
	private String content;
	private BigDecimal productionId;
	private BigDecimal userId;
	private Date createdDate;
	private BigDecimal status;
	
	private User commentUser;
	
	public User getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getRefId() {
		return refId;
	}
	public void setRefId(BigDecimal refId) {
		this.refId = refId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getProductionId() {
		return productionId;
	}
	public void setProductionId(BigDecimal productionId) {
		this.productionId = productionId;
	}
	public BigDecimal getUserId() {
		return userId;
	}
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public BigDecimal getStatus() {
		return status;
	}
	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}
