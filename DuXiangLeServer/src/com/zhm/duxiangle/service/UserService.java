package com.zhm.duxiangle.service;

import com.zhm.duxiangle.bean.UserInfo;

/**
 * ����㣬�û��߼�
 * 
 * @author zhuanghm
 *
 */
public interface UserService {
	/**
	 * �����û�id��ȡ�û���Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserId(String userId);
}
