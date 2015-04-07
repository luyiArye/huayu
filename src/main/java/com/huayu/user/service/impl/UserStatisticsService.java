package com.huayu.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.Production;
import com.huayu.production.bean.vo.ProductionComment;
import com.huayu.production.bean.vo.ProductionOperation;
import com.huayu.production.dao.mapper.ProductionCommentMapper;
import com.huayu.production.dao.mapper.ProductionMapper;
import com.huayu.production.dao.mapper.ProductionOperationMapper;
import com.huayu.user.bean.UserStatistics;
import com.huayu.user.service.IUserStatisticsService;

/**
 * 用户相关统计
 * @author Administrator
 *
 */
@Service
public class UserStatisticsService implements IUserStatisticsService{
	@Autowired
	private ProductionMapper productionMapper;
	@Autowired
	private ProductionOperationMapper productionOperationMapper;
	@Autowired
	private ProductionCommentMapper productionCommentMapper;
	
	/**
	 * 用户相关统计
	 * @param user
	 * @return
	 * @throws ApplicationException
	 */
	public UserStatistics statistics(User user) throws ApplicationException{
		UserStatistics userStatistics=new UserStatistics();
		
		//查询用户发表的作品数
		Production production=new Production();
		production.setCreatedBy(user.getId());
		userStatistics.setProductionCount(productionMapper.selectProductionCount(production));
		
		//查询用户总评论数
		ProductionComment productionComment=new ProductionComment();
		productionComment.setUserId(user.getId());
		userStatistics.setCommentCount(productionCommentMapper.selectProductionCommentCount(productionComment));
		
		//统计用户操作作品的次数
		ProductionOperation productionOperation=new ProductionOperation();
		productionOperation.setUserId(user.getId());
		List<ProductionOperation> poList=productionOperationMapper.statisticsProductionOperation(productionOperation);
		if(poList!=null && !poList.isEmpty()){
			for(ProductionOperation po: poList){
				int oCount=po.getOperationCount();
				if(HuayuConstants.PRODUCTION_PRAISE_OPERATION.equals(po.getOperationType())){
					//赞
					userStatistics.setPraiseCount(oCount);
				}
				else if(HuayuConstants.PRODUCTION_TRAMPLE_OPERATION.equals(po.getOperationType())){
					//踩
					userStatistics.setTrampleCount(oCount);
				}
			}
		}
		
		return userStatistics;
	}
}
