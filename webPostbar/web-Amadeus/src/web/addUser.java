package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addUser extends HttpServlet {

	/**
		addUser
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��������ı��뼯
		request.setCharacterEncoding("utf8");
		String nameString=request.getParameter("name");
		byte[] bs=nameString.getBytes("iso8859-1");
		nameString=new String(bs,"utf8");
		//������Ӧ�ı��뼯
		response.setContentType("text/html;charset=utf8");
		//��ȡ������Դ·��
		String uriString=request.getRequestURI();
		PrintWriter out = response.getWriter();
		System.out.print(uriString);
		out.println(nameString);
		out.println("�ұ�1");
		out.flush();
		out.close();
	}
	public void getDo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//��������ı��뼯
		request.setCharacterEncoding("utf8");
		String nameString=request.getParameter("name");
		byte[] bs=nameString.getBytes("iso8859-1");
		nameString=new String(bs,"utf8");
		//������Ӧ�ı��뼯
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		
		out.println(nameString);
		out.println("�ұ�3");
		out.flush();
		out.close();
	}

}

