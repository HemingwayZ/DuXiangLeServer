package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Url;

import com.zhm.duxiangle.utils.TextUtils;

/**
 * Servlet implementation class DouBanServlet
 */
@WebServlet("/DouBanServlet")
public class DouBanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DouBanServlet() {
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
		// GET https://api.douban.com/v2/book/search
		// 参数 意义 备注
		// q 查询关键字 q和tag必传其一
		// tag 查询的tag q和tag必传其一
		// start 取结果的offset 默认为0
		// count 取结果的条数 默认为20，最大为100
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String q = request.getParameter("q");
		// if (TextUtils.isEmpty(q)) {
		// out.println("q is null");
		// return;
		// }
		String url = "https://api.douban.com/v2/book/search?apikey=028f9ddc0036915507aed99400a3b3ff&q='"+q+"'&start=0&count=9";
		System.out.println("url:" + url);
		response.sendRedirect(url);
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
