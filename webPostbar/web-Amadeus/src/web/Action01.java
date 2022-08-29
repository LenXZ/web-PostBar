package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import dao.EmpDaoImpl;
import entity.*;
public class Action01 extends HttpServlet {

	/**
		������һ����service 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��������ı��뼯
		request.setCharacterEncoding("utf8");
		//������Ӧ�ı��뼯
		response.setContentType("text/html;charset=utf8");
		String uriString=request.getRequestURI();	
		//��ȡ·�����һ��"/"��"."֮����ַ���
		String action=uriString.substring(uriString.lastIndexOf("/")+1,uriString.indexOf("."));
//		System.out.println(action);
		if(action.equals("list")){
			EmpDao dao=new EmpDaoImpl();
			List<Emp> list=dao.findAll();
			//�����ݵ�request�� ���� ������
			request.setAttribute("list", list);
			//ת��
			request.getRequestDispatcher("list.jsp").forward(request, response);
			
		}else if (action.equals("add")) {
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String genderString=request.getParameter("genderString");
			int salary=Integer.parseInt(request.getParameter("salary"));
			Emp emp=new Emp(idString, nameString, genderString, salary);
			EmpDao dao=new EmpDaoImpl();
			dao.addEmp(emp);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//�ض���
			response.sendRedirect("list.do");
		
		}else if(action.equals("load")){
			//��ȡid 
			String idString=request.getParameter("idString");
			//����dao����
			EmpDao dao=new	EmpDaoImpl();
			Emp emp=dao.findById(idString);
			request.setAttribute("emp", emp);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			//�ض���
//			response.sendRedirect("list.do");
		}else if(action.equals("update")){
			//��ȡid Integer.parseInt()
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String genderString=request.getParameter("genderString");
			int salary=Integer.parseInt(request.getParameter("salary"));
			//����dao����
			EmpDao dao=new EmpDaoImpl();
			Emp emp=new Emp(idString, nameString, genderString, salary);
			System.out.println(emp);
			//ִ��sqlɾ��Ԫ��
			dao.modifyEmp(emp);
			dao.findById(idString);
			//�ض���
			response.sendRedirect("list.do");
		}else if(action.equals("delete")){
			//��ȡid Integer.parseInt()
			String idString=request.getParameter("idString");
			//����dao����
			EmpDao dao=new EmpDaoImpl();
			//ִ��sqlɾ��Ԫ��
			dao.deleteEmp(idString);
			//�ض���
			response.sendRedirect("list.do");
		}
		
	}

}
