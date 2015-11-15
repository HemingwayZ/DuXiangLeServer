package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.Page;

public interface BookDao {
	// 增加一本书籍信息到数据库
	public int addBook(Book book);

	/**
	 * 根据用户id获取书籍列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Book> getBooks(int userId);

	/**
	 * 根据用户id获取书籍列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Book> getBooks(String userId, int thispage, int rowperpage);

	/*
	 * 根据书籍id获取书籍信息
	 */
	public Book getBookById(String id,int userid);

	public int getBooksCount(String userid);

	/**
	 * 根据用户id和书的id从书库删除数据操作
	 * 
	 * @param userid
	 * @param bookid
	 * @return
	 */
	public int removeBook(int userid, int bookid);

	public int getBooksCountByKeyWords(String userid,String keywords);

	public List<Book> getBooksByKeyWords(String userid,String keywords, int thispage, int rowperpage);
}
