package com.huayu.production.bean.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.huayu.core.bean.vo.BaseVO;
import com.huayu.core.bean.vo.User;

/**
 * 画语作品
 * @author Administrator
 *
 */
public class Production extends BaseVO{
	private static final long serialVersionUID = 83038709051514027L;
	
	private BigDecimal id;
	private String title;
	private String content;
	private BigDecimal categoryId;
	private BigDecimal status;
	private BigDecimal createdBy;
	private Date createdDate;
	private BigDecimal lastupdatedBy;
	private Date lastupdatedDate;
	//赞次数
	private int praiseCount;
	//踩次数
	private int trampleCount;
	//浏览次数
	private int browseCount;
	//转载次数
	private int transCount;
	//分享次数
	private int shareCount;
	//收藏次数
	private int collectCount;
	//评论次数
	private int commentCount;
	
	//操作类型
	private BigDecimal operationType;
	
	private User createdByUser;
	private List<ProductionResources> productionResources;
	
	@JsonIgnore
	public BigDecimal getOperationType() {
		return operationType;
	}
	public void setOperationType(BigDecimal operationType) {
		this.operationType = operationType;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}
	public int getTrampleCount() {
		return trampleCount;
	}
	public void setTrampleCount(int trampleCount) {
		this.trampleCount = trampleCount;
	}
	public int getBrowseCount() {
		return browseCount;
	}
	public void setBrowseCount(int browseCount) {
		this.browseCount = browseCount;
	}
	public int getTransCount() {
		return transCount;
	}
	public void setTransCount(int transCount) {
		this.transCount = transCount;
	}
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public int getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}
	public User getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}
	public List<ProductionResources> getProductionResources() {
		return productionResources;
	}
	public void setProductionResources(List<ProductionResources> productionResources) {
		this.productionResources = productionResources;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BigDecimal getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(BigDecimal categoryId) {
		this.categoryId = categoryId;
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
	public BigDecimal getLastupdatedBy() {
		return lastupdatedBy;
	}
	public void setLastupdatedBy(BigDecimal lastupdatedBy) {
		this.lastupdatedBy = lastupdatedBy;
	}
	public Date getLastupdatedDate() {
		return lastupdatedDate;
	}
	public void setLastupdatedDate(Date lastupdatedDate) {
		this.lastupdatedDate = lastupdatedDate;
	}

}
