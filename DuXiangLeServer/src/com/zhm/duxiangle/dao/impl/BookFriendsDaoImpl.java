package com.zhm.duxiangle.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.BookFriendsDao;
import com.zhm.duxiangle.utils.DaoUtils;

public class BookFriendsDaoImpl implements BookFriendsDao {
	String sql = "";
	QueryRunner runner = new QueryRunner(DaoUtils.getSource());

	@Override
	public List<Book> getBooksByBookid(int bookid) {
		sql = "select * from book where bookid = ?";
		try {
			return runner.query(sql, new BeanListHandler<Book>(Book.class), bookid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserInfo> getFriendsByIsbn(String isbn) {
		List<Book> books = getBooksByIsbn(isbn);
		if (books == null || books.size() <= 0) {
			return null;
		}
		List<Integer> list = new ArrayList<>();
		sql = "select * from userinfo where userid = ?";
		list.add(books.get(0).getUserId());
		for (int i = 1; i < books.size(); i++) {
			sql += " or userid = ?";
			list.add(books.get(i).getUserId());
		}
		try {
			return runner.query(sql, new BeanListHandler<UserInfo>(UserInfo.class), list.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getBooksByIsbn(String isbn) {
		sql = "select * from book where isbn13 = ? or isbn10 = ?";
		try {
			return runner.query(sql, new BeanListHandler<Book>(Book.class), isbn, isbn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
