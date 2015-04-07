package com.huayu.production.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.ProductionResources;
import com.huayu.production.dao.mapper.ProductionResourcesMapper;
import com.huayu.production.service.IProductionResourcesService;

@Service
public class ProductionResourcesService implements IProductionResourcesService {
	@Autowired
	private ProductionResourcesMapper productionResourcesMapper;
	
	/**
	 * 查询作品作品资源
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午8:46:29
	 * @param productionResources
	 * @return
	 */
	public List<ProductionResources> selectProductionResources(int productionId) throws ApplicationException{
		ProductionResources productionResources=new ProductionResources();
		productionResources.setProductionId(new BigDecimal(productionId));
		return productionResourcesMapper.selectProductionResources(productionResources);
	}
}
