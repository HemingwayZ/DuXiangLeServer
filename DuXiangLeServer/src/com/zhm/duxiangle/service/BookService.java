package com.zhm.duxiangle.service;

import java.util.List;

import com.zhm.duxiangle.bean.Book;

public interface BookService {
	/**
	 * �鼮�������
	 * @param book
	 * @return
	 */
	public int addBook(Book book);
	
	/**
	 * �����û�id��ȡ�鼮�б�
	 * 
	 * @param userId
	 * @return
	 */
	public List<Book> getBooks(String userId);
}
