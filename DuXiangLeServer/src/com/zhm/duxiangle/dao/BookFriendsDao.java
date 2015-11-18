package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.UserInfo;

public interface BookFriendsDao {
	/**
	 * 通过书籍的id获取书籍的详细信息
	 * @param bookid
	 * @return
	 */
	public List<Book> getBooksByBookid(int bookid);
	/**
	 * 根据isbn获取收藏同一本书的书友
	 * @param bookid
	 * @return
	 */
	public List<UserInfo> getFriendsByIsbn(String isbn);
	
	/**
	 * 通过书籍的id获取书籍的详细信息
	 * @param bookid
	 * @return
	 */
	public List<Book> getBooksByIsbn(String isbn);
}
