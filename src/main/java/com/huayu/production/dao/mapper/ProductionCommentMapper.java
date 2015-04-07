package com.huayu.production.dao.mapper;

import java.util.List;

import com.huayu.production.bean.vo.ProductionComment;

/**
 * 作品评论
 * @author arye.luyi Administrator
 * @date 2015-3-14 上午10:40:45
 */
public interface ProductionCommentMapper {
	/**
	 * 查询作品评论
	 */
	public List<ProductionComment> selectProductionComment(ProductionComment productionComment);
	
	/**
	 * 查询作品评论总行数
	 */
	public int selectProductionCommentCount(ProductionComment productionComment);
	
	/**
	 * 新增评论
	 * @author arye.luyi Administrator
	 * @date 2015-3-16 上午10:10:23
	 * @param productionComment
	 * @return
	 */
	public int insertProductionComment(ProductionComment productionComment);
}
