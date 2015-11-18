package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.BookFriendsDao;
import com.zhm.duxiangle.dao.impl.BookFriendsDaoImpl;
import com.zhm.duxiangle.utils.TextUtils;

import io.rong.util.GsonUtil;

/**
 * 用于操作拥有了同一本书的用户的信息，以书会友 关键api接口
 * Servlet implementation class BookFriends
 */
@WebServlet("/BookFriends")
public class BookFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookFriends() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//根据书籍的id获取书籍的信息
		String isbn = request.getParameter("isbn");
		if(TextUtils.isEmpty(isbn)){
			out.print("isbn is null");
			return;
		}
		BookFriendsDao dao = new BookFriendsDaoImpl();
		List<UserInfo> friends = dao.getFriendsByIsbn(isbn);
		String json = GsonUtil.toJson(friends);
		out.println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
