package com.zhm.duxiangle.service;

import java.util.List;

import com.zhm.duxiangle.bean.Book;

public interface BookService {
	/**
	 * 书籍插入操作
	 * @param book
	 * @return
	 */
	public int addBook(Book book);
	
	/**
	 * 根据用户id获取书籍列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Book> getBooks(String userId);
}
