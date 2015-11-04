package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zhm.duxiangle.bean.Page;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.dao.impl.UserDaoImpl;
import com.zhm.duxiangle.service.UserService;
import com.zhm.duxiangle.service.impl.UserServiceImpl;
import com.zhm.duxiangle.utils.TextUtils;

import io.rong.util.GsonUtil;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoServlet() {
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
		UserService service = new UserServiceImpl();

		// 行为
		String action = request.getParameter("action");
		if (TextUtils.isEmpty(action)) {
			out.print("action is null");
			return;
		}
		// 分页获取用户信息
		if ("userinfopage".equals(action)) {
			String thispage = request.getParameter("thispage");
			String rowperpage = request.getParameter("rowperpage");
			if (TextUtils.isEmpty(thispage)) {
				out.println("thispage is null");
				return;
			}
			if (TextUtils.isEmpty(rowperpage)) {
				out.println("rowperpage is null");
				return;
			}
			int iThispage = Integer.valueOf(thispage);
			int iRowperpage = Integer.valueOf(rowperpage);
			if (iThispage <= 0) {
				iThispage = 0;
			}
			Page userinfoPage = service.getUserInfoListByPage(iThispage, iRowperpage);
			out.println(GsonUtil.toJson(userinfoPage));
			return;
		}
		if("update".equals(action)){
			//修改用户信息
			
			return;
		}
		String userId = request.getParameter("userid");
		UserInfo userInfo = service.getUserInfoByUserId(userId);
		if (null == userInfo) {
			out.println("no found");
			return;
		}
		Gson gson = new Gson();
		String json = gson.toJson(userInfo);
		out.println(json);
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
