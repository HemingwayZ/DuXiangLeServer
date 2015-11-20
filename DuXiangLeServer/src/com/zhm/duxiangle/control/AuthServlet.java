package com.zhm.duxiangle.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.zhm.duxiangle.bean.Auth;
import com.zhm.duxiangle.bean.QQUserInfo;
import com.zhm.duxiangle.bean.SinaUserInfo;
import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.AuthDao;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.dao.impl.AuthDaoImpl;
import com.zhm.duxiangle.dao.impl.UserDaoImpl;
import com.zhm.duxiangle.service.UserService;
import com.zhm.duxiangle.service.impl.UserServiceImpl;
import com.zhm.duxiangle.utils.TextUtils;

import io.rong.util.GsonUtil;

/**
 * 用于qq认证 分享，sina认证 分享等操作的类 Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");
		if (TextUtils.isEmpty(action)) {
			out.print("action is null");
			return;
		}
		// qq认证登录的情况
		if ("auth_by_qq".equals(action)) {
			String openid = request.getParameter("openid");
			if (TextUtils.isEmpty(openid)) {
				out.println("openid is null");
				return;
			}
			String access_token = request.getParameter("access_token");
			if (TextUtils.isEmpty(access_token)) {
				out.println("access_token is null");
				return;
			}
			UserDao dao = new UserDaoImpl();
			if (null != dao.getUserByOpenid(openid)) {
				String json = GsonUtil.toJson(dao.getUserByOpenid(openid));
				out.println(json);
				return;
			}
			User user = new User();
			user.setAccess_token(access_token);
			user.setOpenid(openid);
			user.setAuth_type("qq");
			boolean auth = dao.registerByAuth(user);
			User authUser = dao.getUserByOpenid(openid);
			String json = GsonUtil.toJson(authUser);
			out.println(json);
			return;
		}
		if ("qq_update_userinfo".equals(action)) {
			String json = request.getParameter("qquserinfo");
			if (TextUtils.isEmpty(json)) {
				out.println("userinfo is null");
				return;
			}
			UserDao dao = new UserDaoImpl();
			System.out.println("json:" + json);
			QQUserInfo qqUserinfo = (QQUserInfo) GsonUtil.fromJson(json, QQUserInfo.class);

			if (qqUserinfo != null) {
				UserInfo userInfo = dao.getUserInfoByUserid(qqUserinfo.getUserid());
				UserInfo info = new UserInfo();
				info.setAvatar(qqUserinfo.getFigureurl_qq_2());
				info.setNickname(qqUserinfo.getNickname());
				info.setUserId(qqUserinfo.getUserid());

				if (userInfo == null) {
					int i = dao.insertUserInfo(info);
					out.println(i);
				} else {// 若用户信息表已经存在，则更新用户信息
					dao.updateUserInfoAuthByQQ(info);
				}
			}
			return;
		}
		// 新浪微博认证
		if ("auth_by_sina".equals(action)) {
			String strUser = request.getParameter("user");
			System.out.println("user:" + strUser);

			SinaUserInfo sinaUserInfo = (SinaUserInfo) GsonUtil.fromJson(strUser, SinaUserInfo.class);
			User user = new User();
			user.setAccess_token(sinaUserInfo.getName());
			user.setOpenid(sinaUserInfo.getIdstr());
			user.setAuth_type("sina");
			UserDao dao = new UserDaoImpl();

			// 若用户已存在
			if (null != dao.getUserByOpenid(user.getOpenid())) {
				String json = GsonUtil.toJson(dao.getUserByOpenid(user.getOpenid()));
				out.println(json);
				return;
			}
			boolean auth = dao.registerByAuth(user);
			User authUser = dao.getUserByOpenid(sinaUserInfo.getIdstr());
			//设置用户信息
			UserInfo userinfo = new UserInfo();
			userinfo.setUserId(authUser.getUserId());
			userinfo.setNickname(sinaUserInfo.getName());
			userinfo.setAvatar(sinaUserInfo.getAvatar_hd());
			updateUserInfo(userinfo);

			String json = GsonUtil.toJson(authUser);
			System.out.println(json);
			out.println(json);
			return;
		}
	}

	private int updateUserInfo(UserInfo userInfo) {

		UserService service = new UserServiceImpl();
		UserInfo userInfoByUserId = service.getUserInfoByUserId(String.valueOf(userInfo.getUserId()));
		if (userInfoByUserId != null) {// 若未新用户则无法进入
			if (!TextUtils.isEmpty(userInfoByUserId.getAvatar())) {
				String avatar = userInfoByUserId.getAvatar();
				avatar = userInfo.getAvatar();
				System.out.println("avatar:" + avatar);
				File file = new File(avatar);
				System.out.println(file.getPath());
				if (file.exists()) {
					System.out.println("删除：" + file.delete());
				}
			}
		}
		return service.updateUserInfo(userInfo);
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
