package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.User;

/**
 * 用户
 * 
 * @author zhuanghm
 *
 */
public interface UserDao {
	public List<User> findUserById(int id);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public User findUserByUserName(String userName);

	/**
	 * 增加（注册）用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean registerUser(User user);

	/**
	 * 根据用户名修改用户密码
	 * 
	 * @param username
	 * @return
	 */
	public boolean updatePassword(String username,String password);
	
	
}
