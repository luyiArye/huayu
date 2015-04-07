package com.huayu.production.service;

import java.util.List;

import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.ProductionOperation;

public interface IProductionOperationService {
	/**
	 * 新增作品评论
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:23:56
	 * @param productionOperation
	 * @return
	 */
	public int insertProductionOperation(ProductionOperation productionOperation, User loginUser) throws ApplicationException;
	
	/**
	 * 查询作品操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:24:21
	 * @param productionOperation
	 * @return
	 */
	public List<ProductionOperation> selectProductionOperation(ProductionOperation productionOperation) throws ApplicationException;
	
	/**
	 * 统计作品操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:24:52
	 * @param productionOperation
	 * @return
	 */
	public List<ProductionOperation> statisticsProductionOperation(ProductionOperation productionOperation) throws ApplicationException;
	
	/**
	 * 删除作品
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午4:53:38
	 * @param production
	 * @return
	 */
	public int deleteProduction(ProductionOperation productionOperation, User loginUser) throws ApplicationException;
}
