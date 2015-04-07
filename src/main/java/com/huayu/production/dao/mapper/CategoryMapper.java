package com.huayu.production.dao.mapper;

import java.util.List;

import com.huayu.production.bean.vo.Category;

/**
 * 画语分类
 * @author Administrator
 *
 */
public interface CategoryMapper {
	/**
	 * 查询栏目
	 * @return
	 */
	public List<Category> selectCategory(Category category);
}
