package com.huayu.production.service;

import java.util.List;

import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.ProductionComment;

/**
 * 作品评论
 * @author arye.luyi Administrator
 * @date 2015-3-14 上午10:40:45
 */
public interface IProductionCommentService {
	/**
	 * 查询作品评论
	 */
	public List<ProductionComment> selectProductionComment(ProductionComment productionComment) throws ApplicationException;
	
	/**
	 * 新增评论
	 * @author arye.luyi Administrator
	 * @date 2015-3-16 上午10:10:23
	 * @param productionComment
	 * @return
	 */
	public int insertProductionComment(ProductionComment productionComment, User loginUser) throws ApplicationException;
	
	/**
	 * 查询作品评论总记录数
	 */
	public ProductionComment selectProductionCommentCount(ProductionComment productionComment) throws ApplicationException;
}
