package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.service.UserService;
import com.zhm.duxiangle.service.impl.UserServiceImpl;
import com.zhm.duxiangle.utils.TextUtils;

import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;
import io.rong.util.GsonUtil;

/**
 * Servlet implementation class IoRongServlet 负责与融云服务器交互，获取token
 */
@WebServlet("/IoRongServlet")
public class IoRongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String appKey = "82hegw5uh0bpx";
	private String appSecret = "9Zis0BjmUf1B";
	private String result;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IoRongServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserService service = new UserServiceImpl();
		// 获取用户id
		String userid = request.getParameter("userid");
		if (TextUtils.isEmpty(userid)) {
			out.print("userid is null");
			return;
		}
		UserInfo userInfo = service.getUserInfoByUserId(userid);
		String portraitUri = "";
		try {
			SdkHttpResult sdkHttpResult = ApiHttpClient.getToken(appKey, appSecret,
					String.valueOf(userInfo.getUserId()), userInfo.getNickname(), portraitUri, FormatType.json);
			String json = GsonUtil.toJson(sdkHttpResult, new TypeToken<SdkHttpResult>() {
			}.getType());
			out.println(json);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
