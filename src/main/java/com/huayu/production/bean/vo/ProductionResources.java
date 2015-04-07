package com.huayu.production.bean.vo;

import java.math.BigDecimal;

import com.huayu.core.bean.vo.BaseVO;

/**
 * 作品资源关系
 * @author arye.luyi Administrator
 * @date 2015-3-6 下午3:43:07
 */
public class ProductionResources extends BaseVO{
	private static final long serialVersionUID = 8745795655498315531L;

	//作品id
	private BigDecimal productionId;
	//资源id
	private BigDecimal resourceId;
	//描述
	private String description;
	//序号
	private BigDecimal seqNo;
	
	public BigDecimal getProductionId() {
		return productionId;
	}
	public void setProductionId(BigDecimal productionId) {
		this.productionId = productionId;
	}
	public BigDecimal getResourceId() {
		return resourceId;
	}
	public void setResourceId(BigDecimal resourceId) {
		this.resourceId = resourceId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

}
