package com.zhm.duxiangle.test;

import java.util.List;

import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.FriendsDao;
import com.zhm.duxiangle.dao.impl.FriendsDaoImpl;

public class FriendTest {
	public static void main(String[] args) {
		FriendsDao dao = new FriendsDaoImpl();
		List<UserInfo> list = dao.getFriendsInfo(4);
		System.out.println(list.toString());
		System.out.println(dao.addFriends(1, 5));
	}
}
