package com.huayu.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.production.bean.vo.Production;
import com.huayu.production.bean.vo.ProductionOperation;
import com.huayu.production.dao.mapper.ProductionMapper;
import com.huayu.production.dao.mapper.ProductionOperationMapper;
import com.huayu.production.service.IProductionOperationService;

/**
 * 作品操作
 * @author arye.luyi Administrator
 * @date 2015-3-17 下午2:26:21
 */
@Service
public class ProductionOperationService implements IProductionOperationService {
	@Autowired
	private ProductionOperationMapper productionOperationMapper;
	@Autowired
	private ProductionMapper productionMapper;
	
	/**
	 * 新增作品操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:23:56
	 * @param productionOperation
	 * @return
	 */
	public int insertProductionOperation(ProductionOperation productionOperation, User loginUser) throws ApplicationException{
		//设置操作人id
		productionOperation.setUserId(loginUser.getId());
		if(productionOperationMapper.checkHasOperations(productionOperation)>0){
			throw new ApplicationException(ApplicationExceptionCode.ALREADY_OPREATED_EX_CODE);
		}
		
		Production production=new Production();
		if(loginUser!=null){
			production.setLastupdatedBy(loginUser.getId());
		}
		production.setId(productionOperation.getProductionId());
		//赞操作
		if(HuayuConstants.PRODUCTION_PRAISE_OPERATION.equals(productionOperation.getOperationType())){
			production.setPraiseCount(HuayuConstants.BOOLEAN_TRUE.intValue());
		}
		//踩操作
		else if(HuayuConstants.PRODUCTION_TRAMPLE_OPERATION.equals(productionOperation.getOperationType())){
			production.setTrampleCount(HuayuConstants.BOOLEAN_TRUE.intValue());
		}
		//浏览操作
		else if(HuayuConstants.PRODUCTION_BROWSE_OPERATION.equals(productionOperation.getOperationType())){
			production.setBrowseCount(HuayuConstants.BOOLEAN_TRUE.intValue());
		}
		//转载
		else if(HuayuConstants.PRODUCTION_TRANS_OPERATION.equals(productionOperation.getOperationType())){
			production.setTransCount(HuayuConstants.BOOLEAN_TRUE.intValue());
		}
		//分享
		else if(HuayuConstants.PRODUCTION_SHARE_OPERATION.equals(productionOperation.getOperationType())){
			production.setShareCount(HuayuConstants.BOOLEAN_TRUE.intValue());
		}
		//分享
		else if(HuayuConstants.PRODUCTION_COLLECT_OPERATION.equals(productionOperation.getOperationType())){
			production.setCollectCount(HuayuConstants.BOOLEAN_TRUE.intValue());
		}
		//增加作品操作次数
		productionMapper.increaseOpCount(production);
		
		return productionOperationMapper.insertProductionOperation(productionOperation);
	}
	
	/**
	 * 查询作品操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:24:21
	 * @param productionOperation
	 * @return
	 */
	public List<ProductionOperation> selectProductionOperation(ProductionOperation productionOperation) throws ApplicationException{
		return productionOperationMapper.selectProductionOperation(productionOperation);
	}
	
	/**
	 * 统计作品操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:24:52
	 * @param productionOperation
	 * @return
	 */
	public List<ProductionOperation> statisticsProductionOperation(ProductionOperation productionOperation) throws ApplicationException{
		return productionOperationMapper.statisticsProductionOperation(productionOperation);
	}
	
	/**
	 * 删除作品
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午4:53:38
	 * @param production
	 * @return
	 */
	public int deleteProduction(ProductionOperation productionOperation, User loginUser) throws ApplicationException{
		int result=0;
		
		Production production=new Production();
		production.setLastupdatedBy(loginUser.getId());
		//设置状态为删除状态
		production.setStatus(HuayuConstants.PRODUCTION_DELETE_STATUS);
		result+=productionMapper.updateProduction(production);
		
		//设置操作类型为删除
		productionOperation.setOperationType(HuayuConstants.PRODUCTION_DELETE_OPERATION);
		productionOperation.setUserId(loginUser.getId());
		result+=productionOperationMapper.insertProductionOperation(productionOperation);
		
		return result;
	}
}
