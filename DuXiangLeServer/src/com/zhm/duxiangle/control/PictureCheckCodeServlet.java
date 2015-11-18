package com.zhm.duxiangle.control;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhm.duxiangle.other.IdentifyingCode;

@SuppressWarnings("serial")
@WebServlet("/PictureCheckCodeServlet")
public class PictureCheckCodeServlet extends HttpServlet {

	public PictureCheckCodeServlet() {
		super();
	}

	public void init() throws ServletException {
		super.init();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���ò�����ͼƬ
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// ָ�����ɵ���ӦͼƬ
		response.setContentType("image/jpeg");
		IdentifyingCode idCode = new IdentifyingCode();

		BufferedImage image = new BufferedImage(idCode.getWidth(), idCode.getHeight(), BufferedImage.TYPE_INT_BGR);
		Graphics2D g = image.createGraphics();
		// ����������ʽ
		Font myFont = new Font("����", Font.BOLD, 16);
		// ��������
		g.setFont(myFont);

		g.setColor(idCode.getRandomColor(200, 250));
		// ���Ʊ���
		g.fillRect(0, 0, idCode.getWidth(), idCode.getHeight());

		g.setColor(idCode.getRandomColor(180, 200));
		idCode.drawRandomLines(g, 160);
		//����֤�����ָ����session����
		String strCode = idCode.drawRandomString(4, g);
		HttpSession session=request.getSession(true);  
        session.setAttribute("code", strCode);  
		System.out.println("code"+strCode);
		g.dispose();
		
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
}