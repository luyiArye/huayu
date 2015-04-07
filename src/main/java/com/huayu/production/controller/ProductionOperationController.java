package com.huayu.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huayu.core.HuayuConstants;
import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.annotation.PermissionsResource;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.controller.BaseController;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.ProductionOperation;
import com.huayu.production.service.IProductionOperationService;

@Scope("request")
@Controller
@RequestMapping("/po")
@PermissionsResource(key="production.ProductionOperation", desc="画语作品操作")
public class ProductionOperationController extends BaseController{
	@Autowired
	private IProductionOperationService productionOperationService;
	
	/**
	 * 加载作品操作
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:55:13
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/load", method=RequestMethod.GET)
	public DataResponse load(ProductionOperation productionOperation) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		List<ProductionOperation> poList=productionOperationService.selectProductionOperation(productionOperation);
		dataResponse.setData(poList);
		
		return dataResponse;
	}
	
	/**
	 * 统计
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:57:20
	 * @param productionOperation
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/statistic/{productionId}", method=RequestMethod.GET)
	public DataResponse statistic(ProductionOperation productionOperation) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		List<ProductionOperation> poList=productionOperationService.statisticsProductionOperation(productionOperation);
		dataResponse.setData(poList);
		
		return dataResponse;
	}
	
	/**
	 * 赞
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:58:31
	 * @param productionOperation
	 * @return
	 * @throws ApplicationException
	 */
	@PermissionsResource(key="production.ProductionOperation.praise", desc="赞画语作品")
	@ResponseBody
	@RequestMapping(value="/praise/{productionId}", method=RequestMethod.POST)
	public DataResponse praise(ProductionOperation productionOperation) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		productionOperation.setOperationType(HuayuConstants.PRODUCTION_PRAISE_OPERATION);
		productionOperation.setIpAddress(getClientIP());
		productionOperationService.insertProductionOperation(productionOperation, getLoginUser());
		
		return dataResponse;
	}
	
	/**
	 * 踩
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:58:31
	 * @param productionOperation
	 * @return
	 * @throws ApplicationException
	 */
	@PermissionsResource(key="production.ProductionOperation.trample", desc="踩画语作品")
	@ResponseBody
	@RequestMapping(value="/trample/{productionId}", method=RequestMethod.POST)
	public DataResponse trample(ProductionOperation productionOperation) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		productionOperation.setOperationType(HuayuConstants.PRODUCTION_TRAMPLE_OPERATION);
		productionOperation.setIpAddress(getClientIP());
		productionOperationService.insertProductionOperation(productionOperation, getLoginUser());
		
		return dataResponse;
	}
	
	/**
	 * 踩
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午2:58:31
	 * @param productionOperation
	 * @return
	 * @throws ApplicationException
	 */
	@PermissionsResource(key="production.ProductionOperation.delete", desc="删除画语作品")
	@ResponseBody
	@RequestMapping(value="/delete/{productionId}", method=RequestMethod.POST)
	public DataResponse delete(ProductionOperation productionOperation) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		productionOperation.setIpAddress(getClientIP());
		productionOperationService.deleteProduction(productionOperation, getLoginUser());
		
		return dataResponse;
	}
}
