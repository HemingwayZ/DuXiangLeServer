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
			System.out.println("books--userId:"+userId);
			List<Book> books = service.getBooks(userId);
			String json = GsonUtil.toJson(books);
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
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
