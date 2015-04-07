package com.huayu.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.annotation.OperationTimeInterval;
import com.huayu.core.annotation.PermissionsResource;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.controller.BaseController;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.ProductionComment;
import com.huayu.production.service.IProductionCommentService;

@Scope("request")
@Controller
@RequestMapping("/pc")
@PermissionsResource(key="production.ProductionComment", desc="画语作品评论")
public class ProductionCommentController extends BaseController{
	@Autowired
	private IProductionCommentService productionCommentService;
	
	/**
	 * 查询作品评论
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/load/{page}", method=RequestMethod.GET)
	public DataResponse load(ProductionComment productionComment) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		List<ProductionComment> pcList=productionCommentService.selectProductionComment(productionComment);
		dataResponse.setData(pcList);
		dataResponse.setTc(productionComment.getTotalCount());
		dataResponse.setTp(productionComment.getTotalPage());
		
		return dataResponse;
	}
	
	/**
	 * 保存作品评论
	 * @author arye.luyi Administrator
	 * @date 2015-3-16 上午10:13:46
	 * @param productionComment
	 * @return
	 * @throws ApplicationException
	 */
	@PermissionsResource(key="production.ProductionComment.save", desc="保存作品评论")
	@OperationTimeInterval(value=10)
	@ResponseBody
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public DataResponse save(ProductionComment productionComment) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		productionCommentService.insertProductionComment(productionComment, getLoginUser());
		
		return dataResponse;
	}
	
	/**
	 * 查询评论总记录数
	 * @author arye.luyi Administrator
	 * @date 2015-3-16 下午6:06:49
	 * @param productionComment
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/commentTotal", method=RequestMethod.GET)
	public DataResponse commentTotal(ProductionComment productionComment) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		ProductionComment pc=productionCommentService.selectProductionCommentCount(productionComment);
		dataResponse.setData(pc);
		
		return dataResponse;
	}
}
