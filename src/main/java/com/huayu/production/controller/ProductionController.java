package com.huayu.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huayu.core.HuayuConstants;
import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.annotation.PermissionsResource;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.controller.BaseController;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.Production;
import com.huayu.production.service.IProductionService;

/**
 * 作品
 * @author Administrator
 *
 */
@Scope("request")
@Controller
@RequestMapping("/production")
@PermissionsResource(key="production.Production", desc="画语作品")
public class ProductionController extends BaseController{
	@Autowired
	private IProductionService productionService;
	
	/**
	 * 发表
	 * @return
	 * @throws ApplicationException
	 */
	@PermissionsResource(key="production.Production.publish", desc="发表作品页面")
	@RequestMapping(value="/publish", method=RequestMethod.GET)
	public ModelAndView publish() throws ApplicationException{
		modelAndView.setViewName("production/publish.jsp");
		return modelAndView;
	}
	
	/**
	 * 保存作品
	 * @throws ApplicationException
	 */
	@PermissionsResource(key="production.Production.save", desc="保存作品")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView save(Production production) throws ApplicationException{
		productionService.insertProduction(production, getLoginUser());
		
		modelAndView.addObject(HuayuConstants.PAGE_MESSAGE_KEY, "发表成功！");
		
		return goSuccess();
	}
	
	/**
	 * 加载画语作品
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午4:49:30
	 * @param production
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/load", method=RequestMethod.GET)
	public DataResponse load(Production production) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		List<Production> productionList=productionService.selectProduction(production);
		dataResponse.setData(productionList);
		
		return dataResponse;
	}
	
	/**
	 * 加载画语作品
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午4:49:30
	 * @param production
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/load/{categoryId}", method=RequestMethod.GET)
	public ModelAndView loadByCategory(@PathVariable("categoryId") int categoryId) throws ApplicationException{
		modelAndView.addObject("categoryId", categoryId);
		return index();
	}
	
	/**
	 * 作品详情
	 * @author arye.luyi Administrator
	 * @date 2015-3-7 下午3:49:25
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/details/{id}", method=RequestMethod.GET)
	public ModelAndView details(@PathVariable("id") int id) throws ApplicationException{
		Production production=productionService.details(id, getLoginUser(), getClientIP());
		
		modelAndView.addObject("production", production);
		modelAndView.setViewName("production/details.jsp");
		
		return modelAndView;
	}
	
	/**
	 * 统计作品操作次数
	 * @author arye.luyi Administrator
	 * @date 2015-3-18 下午12:38:11
	 * @param production
	 * @return
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/statisticsPOCount/{id}", method=RequestMethod.GET)
	public DataResponse statisticsPOCount(@PathVariable("id") int id) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		dataResponse.setData(productionService.statisticsPOCount(id));
		
		return dataResponse;
	}
}
