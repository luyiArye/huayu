package com.huayu.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.Category;
import com.huayu.production.dao.mapper.CategoryMapper;
import com.huayu.production.service.ICategoryService;

/**
 * 画语分类
 * @author Administrator
 *
 */
@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * 查询栏目
	 * @return
	 */
	public List<Category> selectCategory(Category category) throws ApplicationException{
		return categoryMapper.selectCategory(category);
	}
}
