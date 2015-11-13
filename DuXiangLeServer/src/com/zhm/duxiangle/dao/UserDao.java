package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.bean.UserInfo;

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
	public User getUserByUserName(String userName);

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
	public boolean updatePassword(String username, String password);

	/**
	 * 根据用户的id获取用户的信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserName(int userId);

	/**
	 * 更新用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfo(UserInfo userInfo);

	/**
	 * 增加用户详情信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public int insertUserInfo(UserInfo userInfo);

	/**
	 * 增加用户详情信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public int insertUserInfoWithPicWall(UserInfo userInfo);
	/**
	 * 分页获取用户信息
	 * @param begin 开始位置（>=0）
	 * @param rowPerPage 每页条数
	 * @return
	 */
	public List<UserInfo> getUserInfoList(int begin,int rowPerPage);

	/**
	 * 获取用户总数
	 * 
	 * @return
	 */
	public int getUserCount();
	
	public int updatePicWall(UserInfo userinfo);
	/**
	 * 根据用户id获取用户信息
	 * @param userInfoId
	 * @return
	 */
	public int findUserInfoByUserInfoId(String userInfoId);

	int updateUserInfoWithoutAvatar(UserInfo userInfo);
	
}
