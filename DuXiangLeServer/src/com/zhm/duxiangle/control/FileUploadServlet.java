package com.zhm.duxiangle.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.service.UserService;
import com.zhm.duxiangle.service.impl.UserServiceImpl;
import com.zhm.duxiangle.utils.IOUtils;
import com.zhm.duxiangle.utils.TextUtils;

/**
 * Servlet implementation class ileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		
		// ���ô洢λ��
		String upload = this.getServletContext().getRealPath("/upload");
		String temp = this.getServletContext().getRealPath("/temp");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 5);// 100k--���뻺�����ڴ�С
		factory.setRepository(new File(temp));

		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("UTF-8");
		fileUpload.setFileSizeMax(1024 * 1024 * 3);//
		fileUpload.setSizeMax(1024 * 1024 * 5);
		System.out.println("upload:"+upload);
		// 5������Ƿ�Ϊ��ȷ�ı��ϴ���ʽ---enctype="multipart/form-data"
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new RuntimeException("��ʹ����ȷ�ı��ύ��ʽ");
		}
		try {
			List<FileItem> list = fileUpload.parseRequest(request);

			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					// �ֶ�
				} else {
					// �ļ�
					String name = fileItem.getName();
					String strUUID = UUID.randomUUID().toString();
					if (name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".gif")) {
						strUUID += "_picture" + name.substring(name.lastIndexOf("."));
					
						// �����ļ������
						try {
							InputStream is = fileItem.getInputStream();// ��ȡ�ļ���
							OutputStream os = new FileOutputStream(new File(upload, strUUID));
							// ���ļ��洢����������Ӧλ��
							IOUtils.In2Out(is, os);
							IOUtils.close(is, os);
							// ɾ��temp�л�����ļ�
							fileItem.delete();
							
							//��ͼƬ��Ϣ�洢�����ݿ�
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("������쳣");
						}
					}
				}
			}
		} catch (FileSizeLimitExceededException e) {
			System.out.println("file size is not allowed");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
