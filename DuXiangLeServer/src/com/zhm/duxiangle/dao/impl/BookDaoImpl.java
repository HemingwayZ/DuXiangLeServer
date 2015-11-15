package com.zhm.duxiangle.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.Page;
import com.zhm.duxiangle.dao.BookDao;
import com.zhm.duxiangle.utils.DaoUtils;
import com.zhm.duxiangle.utils.TextUtils;

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
	public Book getBookById(String id,int userid) {
		// TODO Auto-generated method stub
		sql = "select * from book where id = ? and userid = ?";

		try {
			return runner.query(sql, new BeanHandler<Book>(Book.class), id,userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int getBooksCount(String userid) {
		sql = "select count(*) from book where userid=?";
		try {
			return ((Long) (runner.query(sql, new ScalarHandler(), userid))).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Book> getBooks(String userId, int thispage, int rowperpage) {
		sql = "select * from book where userId = ? limit ?,?";

		try {
			return runner.query(sql, new BeanListHandler<Book>(Book.class), userId, thispage, rowperpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int removeBook(int userid, int bookid) {
		sql = "delete from book where userid = ? and bookid = ?";

		try {
			return runner.update(sql, userid, bookid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getBooksCountByKeyWords(String userid, String keywords) {
		
		try {
			if(TextUtils.isEmpty(keywords)){
				sql = "select count(*) from book where userid =?";
				return ((Long) (runner.query(sql, new ScalarHandler(), userid))).intValue();
			}else{
			keywords = "%" + keywords + "%";
			//or strauthor like ? or subtitle like ? or publisher like ?
			sql = "select count(*) from book where ( title or subtitle or strAuthor or publisher like ?  ) and userid =?";
			}
			//, keywords, keywords, keywords
			return ((Long) (runner.query(sql, new ScalarHandler(), keywords, userid))).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Book> getBooksByKeyWords(String userid, String keywords, int thispage, int rowperpage) {
		
		try {
			if(TextUtils.isEmpty(keywords)){
				sql = "select * from book where userid =? limit ?,?";
				return runner.query(sql, new BeanListHandler<Book>(Book.class), userid, thispage, rowperpage);

			}else{
			keywords = "%" + keywords + "%";
			//or strauthor like ? or subtitle like ? or publisher like ?
			sql = "select * from book where ( title or subtitle or strAuthor or publisher like ?  ) and userid =? limit ?,?";
			}
			//,keywords,keywords,keywords
			return runner.query(sql, new BeanListHandler<Book>(Book.class),keywords, userid, thispage, rowperpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
