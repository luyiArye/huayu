package com.huayu.production.service;

import java.util.List;

import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;
import com.huayu.production.bean.vo.Production;

public interface IProductionService {
	/**
	 * 添加作品数据
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午3:38:25
	 * @param production
	 * @return
	 */
	public int insertProduction(Production production, User loginUser) throws ApplicationException;
	
	/**
	 * 查询画语作品
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午4:45:42
	 * @param production
	 * @return
	 */
	public List<Production> selectProduction(Production production) throws ApplicationException;
	
	/**
	 * 作品详情
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午4:45:42
	 * @param production
	 * @return
	 */
	public Production details(int id, User loginUser, String ipAddress) throws ApplicationException;
	
	/**
	 * 统计作品操作次数
	 * @author arye.luyi Administrator
	 * @date 2015-3-18 下午12:38:11
	 * @param production
	 * @return
	 */
	public Production statisticsPOCount(int id) throws ApplicationException;
}
