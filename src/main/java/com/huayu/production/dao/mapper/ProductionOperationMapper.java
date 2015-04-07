package com.huayu.production.dao.mapper;

import java.util.List;

import com.huayu.production.bean.vo.ProductionOperation;

public interface ProductionOperationMapper {
	/**
	 * 新增作品评论
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:23:56
	 * @param productionOperation
	 * @return
	 */
	public int insertProductionOperation(ProductionOperation productionOperation);
	
	/**
	 * 查询作品操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:24:21
	 * @param productionOperation
	 * @return
	 */
	public List<ProductionOperation> selectProductionOperation(ProductionOperation productionOperation);
	
	/**
	 * 统计作品操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:24:52
	 * @param productionOperation
	 * @return
	 */
	public List<ProductionOperation> statisticsProductionOperation(ProductionOperation productionOperation);
	
	/**
	 * 判断是否进行了相同的操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:41:58
	 * @param productionOperation
	 * @return
	 */
	public int checkHasOperations(ProductionOperation productionOperation);
}
