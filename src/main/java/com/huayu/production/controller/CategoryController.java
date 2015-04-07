package com.huayu.production.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.Category;
import com.huayu.production.service.ICategoryService;

/**
 * 画语分类
 * @author Administrator
 *
 */
@Scope("request")
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * 加载画语栏目
	 * @return
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/load", method=RequestMethod.GET)
	public List<Category> load(Category category) throws ApplicationException{
		return categoryService.selectCategory(category);
	}
}
