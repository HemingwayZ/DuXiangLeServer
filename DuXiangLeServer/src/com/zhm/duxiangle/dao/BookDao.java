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
	/*
	 * 根据书籍id获取书籍信息
	 */
	public Book getBookById(String id);
}
