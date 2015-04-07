package com.huayu.user.bean;

import java.math.BigDecimal;

import com.huayu.core.bean.vo.BaseVO;

/**
 * 用户相关统计
 * @author Administrator
 *
 */
public class UserStatistics extends BaseVO{
	private static final long serialVersionUID = -1797886290065197735L;

	private BigDecimal userId;
	//作品数
	private int productionCount;
	//评论数
	private int commentCount;
	//赞数
	private int praiseCount;
	//踩数
	private int trampleCount;
	
	public BigDecimal getUserId() {
		return userId;
	}
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
	public int getProductionCount() {
		return productionCount;
	}
	public void setProductionCount(int productionCount) {
		this.productionCount = productionCount;
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
}
