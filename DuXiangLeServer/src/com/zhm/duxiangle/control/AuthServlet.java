package com.zhm.duxiangle.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhm.duxiangle.bean.Auth;
import com.zhm.duxiangle.bean.QQUserInfo;
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
 * ����qq��֤ ����sina��֤ ����Ȳ������� Servlet implementation class AuthServlet
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
		// qq��֤��¼�����
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
				} else {//���û���Ϣ���Ѿ����ڣ�������û���Ϣ
					dao.updateUserInfoAuthByQQ(info);
				}
			}
		}
	}

	private int updateUserInfo(UserInfo userInfo) {

		UserService service = new UserServiceImpl();
		UserInfo userInfoByUserId = service.getUserInfoByUserId(String.valueOf(userInfo.getUserId()));
		if (userInfoByUserId != null) {// ��δ���û����޷�����
			if (!TextUtils.isEmpty(userInfoByUserId.getAvatar())) {
				String avatar = userInfoByUserId.getAvatar();
				avatar = userInfo.getAvatar();
				System.out.println("avatar:" + avatar);
				File file = new File(avatar);
				System.out.println(file.getPath());
				if (file.exists()) {
					System.out.println("ɾ����" + file.delete());
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
