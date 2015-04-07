package com.huayu.core.service;

import java.util.List;

import com.huayu.core.bean.vo.User;
import com.huayu.core.bean.vo.UserHeadIco;
import com.huayu.core.exception.ApplicationException;

/**
 * 用户
 * @author Administrator
 *
 */
public interface IUserService {
	/**
	 * 检查是否存在指定用户
	 * @param user
	 * @return
	 */
	public boolean checkExistsUser(User user) throws ApplicationException;
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user) throws ApplicationException;
	
	/**
	 * 查询用户信息
	 * @param user
	 * @return
	 */
	public List<User> selectUser(User user) throws ApplicationException;
	
	/**
	 * 登录验证
	 * @return
	 * @throws ApplicationException
	 */
	public User checkLogin(User user) throws ApplicationException;
	
	/**
	 * 查询用户头像
	 * @param userHeadIco
	 * @return
	 */
	public UserHeadIco selectHeadIco(UserHeadIco userHeadIco) throws ApplicationException;
	
	/**
	 * 根据id查询用户信息
	 * @param user
	 * @return
	 */
	public User selectUserById(User user) throws ApplicationException;
}
