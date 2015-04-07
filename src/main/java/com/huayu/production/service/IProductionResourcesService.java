package com.huayu.production.service;

import java.util.List;

import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.ProductionResources;

public interface IProductionResourcesService {
	/**
	 * 查询作品作品资源
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午8:46:29
	 * @param productionResources
	 * @return
	 */
	public List<ProductionResources> selectProductionResources(int productionId) throws ApplicationException;
}
