package com.huayu.production.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.production.bean.vo.Production;
import com.huayu.production.bean.vo.ProductionOperation;
import com.huayu.production.bean.vo.ProductionResources;
import com.huayu.production.dao.mapper.ProductionMapper;
import com.huayu.production.dao.mapper.ProductionOperationMapper;
import com.huayu.production.dao.mapper.ProductionResourcesMapper;
import com.huayu.production.service.IProductionService;
/**
 * 作品
 * @author Administrator
 *
 */
@Service
public class ProductionService implements IProductionService {
	@Autowired
	private ProductionMapper productionMapper;
	@Autowired
	private ProductionResourcesMapper productionResourcesMapper;
	@Autowired
	private ProductionOperationMapper productionOperationMapper;
	
	/**
	 * 添加作品数据
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午3:38:25
	 * @param production
	 * @return
	 */
	public int insertProduction(Production production, User loginUser) throws ApplicationException{
		int result=0;
		
		//设置作品创建人为登录人
		production.setCreatedByUser(loginUser);
		production.setCreatedBy(loginUser.getId());
		//添加作品数据
		result+=productionMapper.insertProduction(production);
		
		BigDecimal productionId=production.getId();
		List<ProductionResources> prList=production.getProductionResources();
		if(prList!=null && !prList.isEmpty()){
			for(ProductionResources pr: prList){
				//设置资源对一个的作品
				pr.setProductionId(productionId);
				result+=productionResourcesMapper.insertProductionResources(pr);
			}
		}
		
		return result;
	}
	
	/**
	 * 查询画语作品
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午4:45:42
	 * @param production
	 * @return
	 */
	public List<Production> selectProduction(Production production) throws ApplicationException{
		return productionMapper.selectProduction(production);
	}
	
	/**
	 * 作品详情
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午4:45:42
	 * @param production
	 * @return
	 */
	public Production details(int id, User loginUser, String ipAddress) throws ApplicationException{
		BigDecimal productionId=new BigDecimal(id);
		Production production=new Production();
		production.setId(productionId);
		
		//增加作品浏览次数
		if(loginUser!=null){
			production.setLastupdatedBy(loginUser.getId());
		}
		production.setBrowseCount(HuayuConstants.BOOLEAN_TRUE.intValue());
		productionMapper.increaseOpCount(production);
		
		//新增作品浏览记录
		ProductionOperation productionOperation=new ProductionOperation();
		productionOperation.setProductionId(productionId);
		if(loginUser!=null){
			productionOperation.setUserId(loginUser.getId());
		}
		productionOperation.setIpAddress(ipAddress);
		productionOperation.setOperationType(HuayuConstants.PRODUCTION_BROWSE_OPERATION);
		productionOperationMapper.insertProductionOperation(productionOperation);
		
		List<Production> pList=productionMapper.selectProduction(production);
		if(pList==null || pList.isEmpty()){
			throw new ApplicationException(ApplicationExceptionCode.PRODUCTION_NOT_FOUND_EX_CODE);
		}
		return pList.get(0);
	}
	
	/**
	 * 统计作品操作次数
	 * @author arye.luyi Administrator
	 * @date 2015-3-18 下午12:38:11
	 * @param production
	 * @return
	 */
	public Production statisticsPOCount(int id) throws ApplicationException{
		return productionMapper.statisticsPOCount(id);
	}
}
