package com.huayu.production.dao.mapper;

import java.util.List;

import com.huayu.production.bean.vo.Production;

/**
 * 作品
 * @author Administrator
 *
 */
public interface ProductionMapper {
	/**
	 * 添加作品数据
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午3:38:25
	 * @param production
	 * @return
	 */
	public int insertProduction(Production production);
	
	/**
	 * 查询画语作品
	 * @author arye.luyi Administrator
	 * @date 2015-3-6 下午4:45:42
	 * @param production
	 * @return
	 */
	public List<Production> selectProduction(Production production);
	
	/**
	 * 修改作品
	 * @author arye.luyi Administrator
	 * @date 2015-3-17 下午4:53:38
	 * @param production
	 * @return
	 */
	public int updateProduction(Production production);
	
	/**
	 * 增加作品操作次数
	 * @author arye.luyi Administrator
	 * @date 2015-3-18 上午10:54:19
	 * @param production
	 * @return
	 */
	public int increaseOpCount(Production production);
	
	/**
	 * 减少作品操作次数
	 * @author arye.luyi Administrator
	 * @date 2015-3-18 上午10:54:54
	 * @param production
	 * @return
	 */
	public int reduceOpCount(Production production);
	
	/**
	 * 统计作品操作次数
	 * @author arye.luyi Administrator
	 * @date 2015-3-18 下午12:38:11
	 * @param production
	 * @return
	 */
	public Production statisticsPOCount(int id);
	
	/**
	 * 查询作品数量
	 * @param production
	 * @return
	 */
	public int selectProductionCount(Production production);
}
