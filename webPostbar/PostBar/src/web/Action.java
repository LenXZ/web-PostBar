package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action extends HttpServlet {


	/**
	 * ������Դ��hello*/
	//	����  ��Ӧ
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��������ı��뼯
		request.setCharacterEncoding("utf8");
		//������Ӧ�ı��뼯
		response.setContentType("text/html;charset=utf8");
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
		String timeString=simpleDateFormat.format(date);		
		PrintWriter out = response.getWriter();
		out.println(timeString);
		out.print("web-Amadeus");
		out.flush();
		out.close();
	}

}
