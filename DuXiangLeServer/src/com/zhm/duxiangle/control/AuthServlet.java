package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhm.duxiangle.bean.Auth;
import com.zhm.duxiangle.dao.AuthDao;
import com.zhm.duxiangle.dao.impl.AuthDaoImpl;
import com.zhm.duxiangle.utils.TextUtils;

/**
 * 用于qq认证 分享，sina认证 分享等操作的类
 * Servlet implementation class AuthServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		if(TextUtils.isEmpty(action)){
			out.print("action is null");
			return;
		}
		//qq认证登录的情况
		if("auth_by_qq".equals(action)){
			String openid = request.getParameter("openid");
			if(TextUtils.isEmpty(openid)){
				out.println("openid is null");
				return;
			}
			String access_token = request.getParameter("access_token");
			if(TextUtils.isEmpty(access_token)){
				out.println("access_token is null");
				return;
			}
			AuthDao dao = new AuthDaoImpl();
			Auth auth =  new Auth();
			auth.setAccess_token(access_token);
			auth.setOpenid(openid);
			int i = dao.insertAuth(auth);
			out.println(i);
			return;
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
