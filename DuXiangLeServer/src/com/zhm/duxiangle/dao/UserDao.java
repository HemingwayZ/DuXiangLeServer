package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.User;

/**
 * 用户
 * @author zhuanghm
 *
 */
public interface UserDao {
	public List<User> findUserById(int id);
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public User findUserByUserName(String userName);
}
