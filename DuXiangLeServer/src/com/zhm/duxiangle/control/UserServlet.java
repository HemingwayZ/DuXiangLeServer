package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.security.MD5Encoder;

import com.google.gson.Gson;
import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.dao.impl.UserDaoImpl;
import com.zhm.duxiangle.utils.MD5Util;
import com.zhm.duxiangle.utils.TextUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (TextUtils.isEmpty(username)) {
			out.print("username is null");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			out.print("password is null");
			return;
		}
		// when username!=null && password!=null
		UserDao dao = new UserDaoImpl();
		User user = dao.findUserByUserName(username == null ? "" : username);
		if (null == user) {
			out.print("no found");
			return;
		}
		if (!password.equals(user.getPassword())) {
			out.print("error password");
			return;
		}
		user.setPassword(MD5Util.endodeStr2MD5(password));
		user.setStatus("online");// 修改用户为登录状态
		// 用户存在且密码正确
		Gson gson = new Gson();
		String json = gson.toJson(user);
		out.print(json);
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
