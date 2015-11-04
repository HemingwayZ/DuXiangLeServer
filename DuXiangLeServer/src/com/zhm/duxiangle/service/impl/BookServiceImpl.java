package com.zhm.duxiangle.service.impl;

import java.util.List;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.dao.BookDao;
import com.zhm.duxiangle.dao.impl.BookDaoImpl;
import com.zhm.duxiangle.service.BookService;
import com.zhm.duxiangle.utils.TextUtils;

public class BookServiceImpl implements BookService {
	BookDao dao = new BookDaoImpl();
	String sql = "";
	
	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		if (book == null) {
			return 0;
		}
		Book book2 = dao.getBookById(book.getId());
		if (book2 != null) {
			return 0;
		}
		return dao.addBook(book);
	}

	@Override
	public List<Book> getBooks(String userId) {
		if(TextUtils.isEmpty(userId)){
			return null;
		}
		return dao.getBooks(Integer.valueOf(userId));
	}

}
