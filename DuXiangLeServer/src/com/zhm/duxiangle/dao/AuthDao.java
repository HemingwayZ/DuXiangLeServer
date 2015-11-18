package com.zhm.duxiangle.dao;

import com.zhm.duxiangle.bean.Auth;

public interface AuthDao {
	/**
	 * 通过open获取qq的认证信息
	 * @param openid
	 * @return
	 */
	public Auth getAuthByOpenid(String openid);
	/**
	 * 插入操作
	 * @param auth
	 * @return
	 */
	public int insertAuth(Auth auth);
}
