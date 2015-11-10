package com.zhm.duxiangle.service;

import java.util.List;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.Page;

public interface BookService {
	/**
	 * �鼮�������
	 * 
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

	/**
	 * �����û�id��ȡ�鼮��ҳ��Ϣ
	 * 
	 * @param userid
	 * @return
	 */
	public Page getBooksByPage(String userid,int thispage,int rowperpage);
	
	/**
	 * �����û�id���鼮idɾ���鼮��Ϣ
	 * @param userid
	 * @param bookid
	 * @return
	 */
	public int removeBook(String userid,String bookid);

	public Page searchBookByKeyWords(String userid,String keywords,int thispage,int rowperpage);
	
}
