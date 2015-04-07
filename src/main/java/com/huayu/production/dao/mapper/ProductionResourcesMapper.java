package com.huayu.production.dao.mapper;

import java.util.List;

import com.huayu.production.bean.vo.ProductionResources;

/**
 * 作品资源关系
 * @author arye.luyi Administrator
 * @date 2015-3-6 下午3:43:07
 */
public interface ProductionResourcesMapper {
	/**
	 * 新增作品资源关系
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午3:49:08
	 * @param productionResources
	 * @return
	 */
	public int insertProductionResources(ProductionResources productionResources);
	
	/**
	 * 查询作品作品资源
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午8:46:29
	 * @param productionResources
	 * @return
	 */
	public List<ProductionResources> selectProductionResources(ProductionResources productionResources);
}
