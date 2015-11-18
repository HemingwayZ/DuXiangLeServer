package com.zhm.duxiangle.test;

import java.util.List;

import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.BookFriendsDao;
import com.zhm.duxiangle.dao.impl.BookFriendsDaoImpl;

public class BookFriendsTest {
	public static void main(String[] args) {
		BookFriendsDao dao = new BookFriendsDaoImpl();
		List<UserInfo> friendsByIsbn = dao.getFriendsByIsbn("9787512401136");
		System.out.println(friendsByIsbn.get(0).getUserId());
	}
}
