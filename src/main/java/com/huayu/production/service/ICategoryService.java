package com.huayu.production.service;

import java.util.List;

import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.Category;

/**
 * 画语分类
 * @author Administrator
 *
 */
public interface ICategoryService {
	/**
	 * 查询栏目
	 * @return
	 */
	public List<Category> selectCategory(Category category) throws ApplicationException;
}
