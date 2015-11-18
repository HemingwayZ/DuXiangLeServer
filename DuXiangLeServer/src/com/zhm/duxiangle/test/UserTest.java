package com.zhm.duxiangle.test;

import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.dao.impl.UserDaoImpl;
import com.zhm.duxiangle.service.UserService;
import com.zhm.duxiangle.service.impl.UserServiceImpl;

public class UserTest {
	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();
		// dao.findUserById(1);

		// 将明文密码使用摘要算法进行加密
		// register(dao);
		// updatePassword(dao);
		// getUserInfoByUserName(dao);
		// updateUserInfo(dao);
		// System.out.println(dao.getUserInfoList(0,1).toString());
		UserService service = new UserServiceImpl();
		System.out.println(dao.getUserInfoList(0, 2));
	}

	private static void updateUserInfo(UserDaoImpl dao) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(1);
		userInfo.setUserinfoId(1);
		userInfo.setDescrib("aaaa");
		userInfo.setAvatar("http://localhost:8080/DuXiangLeServer/images/welcome-1.jpg");
		userInfo.setCreated("2015-10-15");
		userInfo.setNickname("庄海明");
		System.out.println(dao.updateUserInfo(userInfo));
	}

	private static void getUserInfoByUserName(UserDaoImpl dao) {
		UserInfo userInfo = dao.getUserInfoByUserid(1);
		System.out.println(userInfo.getNickname());
	}

	private static void updatePassword(UserDaoImpl dao) {
		boolean b = dao.updatePassword("q@qq.com", "qqqqqq");
		if (b) {
			System.out.println("add success");
		} else {
			System.out.println("add failed");
		}
	}

	private static void register(UserDaoImpl dao) {
		User user = new User("zhm1", "zhm1");
		boolean b = dao.registerUser(user);
		if (b) {
			System.out.println("add success");
		} else {
			System.out.println("add failed");
		}
	}
}
