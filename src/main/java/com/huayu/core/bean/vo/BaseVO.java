package com.huayu.core.bean.vo;

import java.io.Serializable;

public abstract class BaseVO implements Serializable{
	private static final long serialVersionUID = -4676646864189741713L;
	
	//每页显示的条数
	private int pageSize=15;
	//当前页
	private int page;
	//总页数
	//private int totalPage;
	//总行数
	private int totalCount;
	//起始行
	//private int start;
	
	/**
	 * 总页数
	 * @author arye.luyi Administrator
	 * @date 2015-3-14 下午2:42:42
	 * @return
	 */
	public int getTotalPage() {
		if(this.totalCount<=0){
			return 0;
		}
		return (totalCount-1)/pageSize+1;
	}
	
	/**
	 * 获取查询起始行
	 * @author arye.luyi Administrator
	 * @date 2015-3-14 下午2:43:15
	 * @return
	 */
	public int getStart() {
		int start=0;
		start=pageSize*(page-1);
		if(start<=0){
			return 0;
		}
		return start;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/*public void setStart(int start) {
		this.start = start;
	}*/
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/
}
