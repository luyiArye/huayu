package com.huayu.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.Production;
import com.huayu.production.bean.vo.ProductionComment;
import com.huayu.production.dao.mapper.ProductionCommentMapper;
import com.huayu.production.dao.mapper.ProductionMapper;
import com.huayu.production.service.IProductionCommentService;

/**
 * 作品评论
 * @author arye.luyi Administrator
 * @date 2015-3-14 上午10:40:45
 */
@Service
public class ProductionCommentService implements IProductionCommentService{
	@Autowired
	private ProductionCommentMapper productionCommentMapper;
	@Autowired
	private ProductionMapper productionMapper;
	
	/**
	 * 查询作品评论
	 */
	public List<ProductionComment> selectProductionComment(ProductionComment productionComment) throws ApplicationException{
		if(productionComment.getTotalCount()<=0){
			int totalCount=productionCommentMapper.selectProductionCommentCount(productionComment);
			//设置总记录数
			productionComment.setTotalCount(totalCount);
		}
		
		return productionCommentMapper.selectProductionComment(productionComment);
	}
	
	/**
	 * 新增评论
	 * @author arye.luyi Administrator
	 * @date 2015-3-16 上午10:10:23
	 * @param productionComment
	 * @return
	 */
	public int insertProductionComment(ProductionComment productionComment, User loginUser) throws ApplicationException{
		productionComment.setUserId(loginUser.getId());
		Production production=new Production();
		production.setId(productionComment.getProductionId());
		
		//增加作品评论次数
		if(loginUser!=null){
			production.setLastupdatedBy(loginUser.getId());
		}
		production.setCommentCount(HuayuConstants.BOOLEAN_TRUE.intValue());
		productionMapper.increaseOpCount(production);
		
		return productionCommentMapper.insertProductionComment(productionComment);
	}
	
	/**
	 * 查询作品评论总记录数
	 */
	public ProductionComment selectProductionCommentCount(ProductionComment productionComment) throws ApplicationException{
		int totalCount=productionCommentMapper.selectProductionCommentCount(productionComment);
		//设置总记录数
		productionComment.setTotalCount(totalCount);
		
		return productionComment;
	}
}
