package com.zhm.duxiangle.test;

import com.zhm.duxiangle.bean.Auth;
import com.zhm.duxiangle.dao.AuthDao;
import com.zhm.duxiangle.dao.impl.AuthDaoImpl;

public class AuthTest {
	public static void main(String[] args) {
		AuthDao dao = new AuthDaoImpl();
		Auth auth = new Auth();
		auth.setAccess_token("token");
		auth.setOpenid("openid");
		auth.setType("qq");
		System.out.println(dao.insertAuth(auth ));
	}
}
