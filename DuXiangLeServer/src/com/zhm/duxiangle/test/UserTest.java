package com.zhm.duxiangle.test;

import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.dao.impl.UserDaoImpl;

public class UserTest {
	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();
//		dao.findUserById(1);
		
		//将明文密码使用摘要算法进行加密
//		register(dao);
		boolean b =dao.updatePassword("q@qq.com", "qqqqqq");
		if(b){
			System.out.println("add success");
		}else{
			System.out.println("add failed");
		}
	}

	private static void register(UserDaoImpl dao) {
		User user = new User("zhm1","zhm1");
		boolean b = dao.registerUser(user);
		if(b){
			System.out.println("add success");
		}else{
			System.out.println("add failed");
		}
	}	
}
