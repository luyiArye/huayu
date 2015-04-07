package com.huayu.core.dao.mapper;

import java.util.List;

import com.huayu.core.bean.vo.User;
import com.huayu.core.bean.vo.UserHeadIco;

public interface UserMapper {
	/**
	 * 检查是否存在指定用户
	 * @param user
	 * @return
	 */
	public long checkExistsUser(User user);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	/**
	 * 查询用户信息
	 * @param user
	 * @return
	 */
	public List<User> selectUser(User user);
	
	/**
	 * 查询用户头像
	 * @param userHeadIco
	 * @return
	 */
	public UserHeadIco selectHeadIco(UserHeadIco userHeadIco);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
}
