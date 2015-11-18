package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.UserInfo;

public interface BookFriendsDao {
	/**
	 * ͨ���鼮��id��ȡ�鼮����ϸ��Ϣ
	 * @param bookid
	 * @return
	 */
	public List<Book> getBooksByBookid(int bookid);
	/**
	 * ����isbn��ȡ�ղ�ͬһ���������
	 * @param bookid
	 * @return
	 */
	public List<UserInfo> getFriendsByIsbn(String isbn);
	
	/**
	 * ͨ���鼮��id��ȡ�鼮����ϸ��Ϣ
	 * @param bookid
	 * @return
	 */
	public List<Book> getBooksByIsbn(String isbn);
}
