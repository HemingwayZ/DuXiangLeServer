package com.zhm.duxiangle.dao;

import com.zhm.duxiangle.bean.Auth;

public interface AuthDao {
	/**
	 * ͨ��open��ȡqq����֤��Ϣ
	 * @param openid
	 * @return
	 */
	public Auth getAuthByOpenid(String openid);
	/**
	 * �������
	 * @param auth
	 * @return
	 */
	public int insertAuth(Auth auth);
}
