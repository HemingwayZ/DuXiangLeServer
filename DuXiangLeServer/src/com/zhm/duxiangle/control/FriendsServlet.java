package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.zhm.duxiangle.bean.Friends;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.FriendsDao;
import com.zhm.duxiangle.dao.impl.FriendsDaoImpl;
import com.zhm.duxiangle.utils.TextUtils;

import io.rong.util.GsonUtil;

/**
 * Servlet implementation class FriendsServlet
 */
@WebServlet("/FriendsServlet")
public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FriendsServlet() {
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
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (TextUtils.isEmpty(action)) {
			out.println("action is null");
			return;
		}
		FriendsDao dao = new FriendsDaoImpl();

		String userid = request.getParameter("userid");

		if (TextUtils.isEmpty(userid)) {
			out.println("userid is null");
			return;
		}
		if ("get_friends".equals(action)) {
			List<Friends> list = dao.getFriendsByUserid(Integer.valueOf(userid));
			String json = GsonUtil.toJson(list, new TypeToken<List<Friends>>() {
			}.getType());

			out.print(json);
			return;
		}
		if ("get_friends_info".equals(action)) {
			List<UserInfo> list = dao.getFriendsInfo(Integer.valueOf(userid));
			String json = GsonUtil.toJson(list, new TypeToken<List<UserInfo>>() {
			}.getType());

			out.print(json);
			return;
		}
		String friendid = request.getParameter("friendid");
		if (TextUtils.isEmpty(friendid)) {
			out.println("friendid is null");
			return;
		}
		if ("add_friend".equals(action)) {

			int i = dao.addFriends(Integer.valueOf(userid), Integer.valueOf(friendid));
			out.print(i);
			return;
		}
		if ("remove_friend".equals(action)) {

			boolean removeMyFriend = dao.removeMyFriend(Integer.valueOf(userid), Integer.valueOf(friendid));
			out.print(removeMyFriend);
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
