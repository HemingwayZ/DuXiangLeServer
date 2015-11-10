package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.zhm.duxiangle.bean.Book;
import com.zhm.duxiangle.bean.Page;
import com.zhm.duxiangle.service.BookService;
import com.zhm.duxiangle.service.impl.BookServiceImpl;
import com.zhm.duxiangle.utils.TextUtils;

import io.rong.util.GsonUtil;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isDoPost = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.sendRedirect("https://api.douban.com/v2/book/isbn/:9787111348665");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		BookService service = new BookServiceImpl();

		String action = request.getParameter("action");
		if (TextUtils.isEmpty(action)) {
			out.println("action is null");
			return;
		}
		if ("books".equals(action)) {
			String userId = request.getParameter("userId");
			System.out.println("books--userId:" + userId);
			List<Book> books = service.getBooks(userId);
			String json = GsonUtil.toJson(books);
			out.println(json);
			return;

		}
		if ("pagebooks".equals(action)) {
			// 分页获取书籍信息
			String userId = request.getParameter("userid");
			String thispage = request.getParameter("thispage");
			String rowperpage = request.getParameter("rowperpage");
			if (TextUtils.isEmpty(userId)) {
				out.println("userid is null");
				return;
			}
			int iThispage = 0;
			int iRowperpage = 1;
			if (TextUtils.isEmpty(thispage)) {
				iThispage = 0;
			} else {
				iThispage = Integer.valueOf(thispage);

			}
			if (TextUtils.isEmpty(rowperpage)) {
				iRowperpage = 1;
			} else {
				iRowperpage = Integer.valueOf(rowperpage);
			}

			System.out.println("books--userId:" + userId + "  thispage rowperpage=" + iThispage + "--" + iRowperpage);
			Page page = service.getBooksByPage(userId, iThispage, iRowperpage);
			String json = GsonUtil.toJson(page);
			out.println(json);
			return;
		}
		if ("save_book".equals(action)) {
			String json = request.getParameter("book");
			System.out.println("book:" + json);
			if (TextUtils.isEmpty(json)) {
				out.println("failed");
				return;
			}
			Book book = (Book) GsonUtil.fromJson(json, new TypeToken<Book>() {
			}.getType());

			int i = service.addBook(book);
			if (i == 0) {
				out.println("failed");
				return;
			}
			out.print("success");
		}
		if ("removebook".endsWith(action)) {
			String userid = request.getParameter("userid");
			String bookid = request.getParameter("bookid");
			System.out.println(userid + " -- " + bookid);
			out.println(service.removeBook(userid, bookid));
		}
		if ("searchbook".equals(action)) {
			// 查找

			String keywords = request.getParameter("keywords");
			String userid = request.getParameter("userid");
			String thispage = request.getParameter("thispage");
			String rowperpage = request.getParameter("rowperpage");
			if (TextUtils.isEmpty(userid)) {
				out.println("userid is null");
				return;
			}
			if (isDoPost==false) {
				keywords = new String(keywords.getBytes("ISO-8859-1"), "UTF-8");
			}
			int iThispage = 0;
			int iRowperpage = 4;
			if (!TextUtils.isEmpty(thispage)) {
				iThispage = Integer.valueOf(thispage);
			}
			if (!TextUtils.isEmpty(rowperpage)) {
				iRowperpage = Integer.valueOf(rowperpage);
			}
			System.out.println("keywords:" + keywords);
			Page bookPage = service.searchBookByKeyWords(userid, keywords, iThispage, iRowperpage);
			
			out.println(GsonUtil.toJson(bookPage));
		}
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		isDoPost = true;
		doGet(request, response);
	}

}
