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

	/**
	 * �����û�id��ȡ�鼮�б�
	 * 
	 * @param userId
	 * @return
	 */
	public List<Book> getBooks(String userId, int thispage, int rowperpage);

	/*
	 * �����鼮id��ȡ�鼮��Ϣ
	 */
	public Book getBookById(String id,int userid);

	public int getBooksCount(String userid);

	/**
	 * �����û�id�����id�����ɾ�����ݲ���
	 * 
	 * @param userid
	 * @param bookid
	 * @return
	 */
	public int removeBook(int userid, int bookid);

	public int getBooksCountByKeyWords(String userid,String keywords);

	public List<Book> getBooksByKeyWords(String userid,String keywords, int thispage, int rowperpage);
}
