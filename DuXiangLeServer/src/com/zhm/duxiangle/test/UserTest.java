package com.zhm.duxiangle.test;

import com.zhm.duxiangle.dao.impl.UserDaoImpl;

public class UserTest {
	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();
		dao.findUserById(1);
	}
}
