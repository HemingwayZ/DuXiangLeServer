package com.zhm.duxiangle.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

/**
 * Servlet implementation class IoRongServlet
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
		String userId = "1";
		String userName = "a@aa.com";
		String portraitUri = "";
		try {
			SdkHttpResult sdkHttpResult = ApiHttpClient.getToken(appKey, appSecret, userId, userName, portraitUri,
					FormatType.json);
			int httpCode = sdkHttpResult.getHttpCode();
			if (httpCode != 200) {
				out.print("IoRong Server Error");
				return;
			}
			String result = sdkHttpResult.getResult();
			out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: " + result).append(request.getContextPath());
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
