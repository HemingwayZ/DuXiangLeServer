package com.zhm.duxiangle.test;

import java.util.List;

import com.sun.java.accessibility.util.Translator;
import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.dao.BookDao;
import com.zhm.duxiangle.dao.impl.BookDaoImpl;

public class BookTest {
	public static void main(String[] args) {
		BookDao dao = new BookDaoImpl();
//		Book book = new Book(3, "id", "title", "subtitle", null, null, "price", "publisher", "catalog", "summary",
//				"author_intro", "isbn10", "isbn13", "url", "alt", null, null, null, "100", "image", 3, "strAuthor",
//				"strTranslator");
//		System.out.println(dao.addBook(book));
		List<Book> book = dao.getBooks(1);
		
		System.out.println(book.toString());
	}
}
