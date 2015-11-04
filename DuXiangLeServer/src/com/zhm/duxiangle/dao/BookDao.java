package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.Page;

public interface BookDao {
	// ����һ���鼮��Ϣ�����ݿ�
	public int addBook(Book book);

	/**
	 * �����û�id��ȡ�鼮�б�
	 * 
	 * @param userId
	 * @return
	 */
	public List<Book> getBooks(int userId);
	/*
	 * �����鼮id��ȡ�鼮��Ϣ
	 */
	public Book getBookById(String id);
}
