package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.User;

/**
 * �û�
 * @author zhuanghm
 *
 */
public interface UserDao {
	public List<User> findUserById(int id);
	
	/**
	 * �����û��������û�
	 * @param userName
	 * @return
	 */
	public User findUserByUserName(String userName);
}
