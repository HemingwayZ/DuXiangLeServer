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
		UserDao dao = new UserDaoImpl();
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (TextUtils.isEmpty(action)) {
			out.println("error action");
			return;
		}
		if (TextUtils.isEmpty(username)) {
			out.print("username is null");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			out.print("password is null");
			return;
		}
		User user = dao.findUserByUserName(username);

		if ("login".equals(action)) {
			if (null == user) {
				out.print("no found");
				return;
			}
			// when username!=null && password!=null
			password = MD5Util.endodeStr2MD5(password);
			if (!password.equals(user.getPassword())) {
				out.print("error password");
				return;
			}

			Gson gson = new Gson();
			String json = gson.toJson(user);

			user.setStatus("online");// 修改用户为登录状态
			// 用户存在且密码正确

			out.print(json);
		} else if ("register".equals(action)) {
			if (null != user) {
				out.print("user is exist");
				return;
			}
			user = new User();
			user.setUserName(username);
			user.setPassword(password);

			boolean b = dao.registerUser(user);
			if (b) {
				// 若用户不存在
				user = dao.findUserByUserName(username);
				Gson gson = new Gson();
				String json = gson.toJson(user);
				out.println(json);
			} else {
				out.println("regeister failed");
			}
		} else if ("update_password".equals(action)) {// 修改你用户密码
			if (null == user) {
				out.print("no found");
				return;
			}

			String newPassword = request.getParameter("new_password");
			if (TextUtils.isEmpty(newPassword)) {
				out.println("new password is null");
				return;
			}
			if (password.equals(newPassword)) {
				out.println("password is the same");
				return;
			}
			// 验证旧密码
			password = MD5Util.endodeStr2MD5(password);
			if (!user.getPassword().equals(password)) {
				out.println("error password");
				return;
			} else {
				// 用户存在
				boolean b = dao.updatePassword(username, newPassword);
				if (b) {
					out.println("update success");
				} else {
					out.println("update failed");
				}
			}

		} else {
			out.println("error action");
		}
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
