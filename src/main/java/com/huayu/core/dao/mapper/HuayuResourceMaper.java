package com.huayu.core.dao.mapper;

import com.huayu.core.bean.vo.HuayuResources;

public interface HuayuResourceMaper {
	/**
	 * 新增资源
	 * @param huayuResources
	 * @return
	 */
	public int insertResource(HuayuResources huayuResources);
	
	/**
	 * 根据id查询资源
	 * @param id
	 * @return
	 */
	public HuayuResources selectResourceById(int id);
}
