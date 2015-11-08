package com.zhm.duxiangle.service.impl;

import java.util.List;

import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.Page;
import com.zhm.duxiangle.bean.UserInfo;
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
		if (TextUtils.isEmpty(userId)) {
			return null;
		}
		return dao.getBooks(Integer.valueOf(userId));
	}

	@Override
	public Page getBooksByPage(String userid, int thispage, int rowperpage) {
		// TODO Auto-generated method stub
		Page page = new Page();
		// 1、当前页面页码
		page.setThispage(thispage);
		// 2、每页记录数
		page.setRowperpage(rowperpage);
		// 3、总记录数
		int countrow = dao.getBooksCount(userid);
		page.setCountrow(countrow);
		// 4、总页数
		int countpage = countrow / (rowperpage == 0 ? 1 : rowperpage) + ((countrow % rowperpage) == 0 ? 0 : 1);
		page.setCountpage(countpage);
		// 5、首页
		page.setFirstpage(1);
		// 6、尾页
		page.setLastpage(countpage);
		// 7、上一页
		page.setPrepage(thispage == 0 ? 0 : thispage - 1);
		// 8、下一页
		page.setNextpage(thispage == countpage ? countpage : thispage + 1);
		// 9、每页内容--客户
		List<Book> book = dao.getBooks(userid, thispage, rowperpage);
		page.setList(book);
		return page;
	}

	@Override
	public int removeBook(String userid, String bookid) {
		// TODO Auto-generated method stub
		if(TextUtils.isEmpty(bookid)||TextUtils.isEmpty(userid)){
			
			return 0;
		}
		return dao.removeBook(Integer.valueOf(userid), Integer.valueOf(bookid));
	}

	@Override
	public Page searchBookByKeyWords(String keywords,int thispage,int rowperpage) {
		// TODO Auto-generated method stub
				Page page = new Page();
				// 1、当前页面页码
				page.setThispage(thispage);
				// 2、每页记录数
				page.setRowperpage(rowperpage);
				// 3、总记录数
				int countrow = dao.getBooksCountByKeyWords(keywords);
				page.setCountrow(countrow);
				// 4、总页数
				int countpage = countrow / (rowperpage == 0 ? 1 : rowperpage) + ((countrow % rowperpage) == 0 ? 0 : 1);
				page.setCountpage(countpage);
				// 5、首页
				page.setFirstpage(1);
				// 6、尾页
				page.setLastpage(countpage);
				// 7、上一页
				page.setPrepage(thispage == 0 ? 0 : thispage - 1);
				// 8、下一页
				page.setNextpage(thispage == countpage ? countpage : thispage + 1);
				// 9、每页内容--客户
				List<Book> book = dao.getBooksByKeyWords(keywords, thispage, rowperpage);
				page.setList(book);
				return page;
	}

}
