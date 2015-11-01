package com.zhm.duxiangle.service.impl;

import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.dao.impl.UserDaoImpl;
import com.zhm.duxiangle.service.UserService;
import com.zhm.duxiangle.utils.TextUtils;

public class UserServiceImpl implements UserService {

	@Override
	public UserInfo getUserInfoByUserId(String userId) {
		// TODO Auto-generated method stub
		if(TextUtils.isEmpty(userId)){
			return null;
		}
		UserDao dao = new UserDaoImpl();
		return dao.getUserInfoByUserName(Integer.valueOf(userId));
	}

}
