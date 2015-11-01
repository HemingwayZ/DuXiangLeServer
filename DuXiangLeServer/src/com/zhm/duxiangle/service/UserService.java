package com.zhm.duxiangle.service;

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
}
