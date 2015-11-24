package com.zhm.duxiangle.service;

import java.util.List;

import com.zhm.duxiangle.bean.Page;
import com.zhm.duxiangle.bean.UserInfo;

/**
 * 服务层，用户逻辑
 * 
 * @author zhuanghm
 *
 */
public interface UserService {
	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserId(String userId);
	/**
	 * 逻辑层-分页获取用户信息逻辑处理
	 * @param begin
	 * @param rowperpage
	 * @return
	 */
	public Page  getUserInfoListByPage(int begin, int rowperpage);
	
	/**
	 * 更新用户信息
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfo(UserInfo userInfo);
	
	/**
	 * 更新照片墙
	 * @param userInfo
	 * @return
	 */
	public int updatePicWall(UserInfo userInfo);
	/**
	 * 更新有用户文字信息
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfoWithoutAvatar(UserInfo userInfo);
	/**
	 * 用户搜索
	 * @param keywords
	 * @return
	 */
	public List<UserInfo> findUserInfoByKeyWords(String keywords);
	/**
	 * 根据书籍的isbn，分页获取收藏了该书的用户信息
	 * @param isbn
	 */
	public Page pageUserInfoByIsbn(String isbn,int thispage,int rowperpage);
}
