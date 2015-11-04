package com.zhm.duxiangle.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.Page;
import com.zhm.duxiangle.dao.BookDao;
import com.zhm.duxiangle.utils.DaoUtils;

public class BookDaoImpl implements BookDao {
	String sql = "";
	QueryRunner runner = new QueryRunner(DaoUtils.getSource());

	@Override
	public int addBook(Book book) {
		// insert into book
		// values(null,2,'第一本书','subtitle','strAuthor','strTranslator','price','publisher','catalog','summary','author_intro','isbn10','isbn13','url','alt','pages','images');
		sql = "insert into book values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Object params[] = new Object[] { book.getId(), book.getTitle(), book.getSubtitle(), book.getStrAuthor(),
				book.getStrTranslator() == null ? "" : book.getStrTranslator(), book.getPrice(), book.getPublisher(),
				book.getCatalog(), book.getSummary(), book.getAuthor_intro(), book.getIsbn10(), book.getIsbn13(),
				book.getUrl(), book.getAlt(), book.getPages(), book.getImage(), book.getUserId() };
		try {
			return runner.update(sql, params);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Book> getBooks(int userId) {
		sql = "select * from book where userId = ?";

		try {
			return runner.query(sql, new BeanListHandler<Book>(Book.class), userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book getBookById(String id) {
		// TODO Auto-generated method stub
		sql = "select * from book where id = ?";

		try {
			return runner.query(sql, new BeanHandler<Book>(Book.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
