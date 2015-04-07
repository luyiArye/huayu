package com.huayu.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.controller.BaseController;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.ProductionResources;
import com.huayu.production.service.IProductionResourcesService;

/**
 * 作品资源
 * @author Administrator
 *
 */
@Scope("request")
@Controller
@RequestMapping("/pr")
public class ProductionResourcesController extends BaseController{
	@Autowired
	private IProductionResourcesService productionResourcesService;
	
	/**
	 * 加载画语作品资源
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午4:49:30
	 * @param production
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/load/{productionId}", method=RequestMethod.GET)
	public DataResponse load(@PathVariable("productionId") int productionId) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		List<ProductionResources> prList=productionResourcesService.selectProductionResources(productionId);
		dataResponse.setData(prList);
		
		return dataResponse;
	}
}
