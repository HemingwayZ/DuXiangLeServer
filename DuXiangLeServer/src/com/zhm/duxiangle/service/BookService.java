package com.zhm.duxiangle.service;

import java.util.List;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.Page;

public interface BookService {
	/**
	 * 书籍插入操作
	 * 
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

	/**
	 * 根据用户id获取书籍分页信息
	 * 
	 * @param userid
	 * @return
	 */
	public Page getBooksByPage(String userid,int thispage,int rowperpage);
	
	/**
	 * 根据用户id和书籍id删除书籍信息
	 * @param userid
	 * @param bookid
	 * @return
	 */
	public int removeBook(String userid,String bookid);

	public Page searchBookByKeyWords(String userid,String keywords,int thispage,int rowperpage);
	
}
