package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDao;
import dao.EmpDaoImpl;
import entity.Emp;

public class Action extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf8");
		response.setContentType
		("text/html;charset=utf8");
		//��ȡ������Դ·��
		String uri=request.getRequestURI();
		String action=uri.substring(
		uri.lastIndexOf("/") + 1,
		uri.lastIndexOf(".")
		);
		if(action.equals("list")){
			EmpDao dao =new EmpDaoImpl();
			List<Emp> list = dao.findAll();
			request.setAttribute
			("list", list);
			//ת��
			request.
			getRequestDispatcher("list.jsp")
			.forward(request,response);
		}else if(action.equals("delete")){
			//��ȡid
			int id = Integer.parseInt(
				request.getParameter("id"));
			//����dao����
			EmpDao dao = new EmpDaoImpl();
			//ִ��sqlɾ��Ԫ��
			dao.deleteEmp(id);
			//�ض���list.doҳ��
			response.sendRedirect
						("list.do");
		}else if(action.equals("add")){
			int id = Integer.parseInt(
				request.getParameter("id"));
			String name = 
				request.getParameter("name");
//			name =new String(name.getBytes
//					("ISO-8859-1"),"UTF8");
			System.out.println(name);
			String gender = 
				request.getParameter("gender");
			int salary = Integer.parseInt(
				request.getParameter("salary"));
			Emp emp= new Emp
			(id,name,gender,salary);
			System.out.println(emp);
			EmpDao dao= new EmpDaoImpl();
			dao.addEmp(emp);
			response.sendRedirect("list.do");
		}else if(action.equals("load")){
			int id = Integer.parseInt(
			request.getParameter("id"));
			EmpDao dao= new EmpDaoImpl();
			Emp emp = dao.findById(id);
			
			request.setAttribute("emp",emp);
			request.getRequestDispatcher
			("update.jsp").forward
			(request,response);
		}else if(action.equals("update")){
			int id= Integer.parseInt(
				request.getParameter("id"));
			String name =request.getParameter("name");
			String gender =request.getParameter("gender");
			int salary = Integer.parseInt(
				request.getParameter("salary"));
			Emp emp =new Emp
			(id,name,gender,salary);
			EmpDao dao=new EmpDaoImpl();
			dao.modifyEmp(emp);
			response.sendRedirect("list.do");
		}
				
				
				
				
				
				
				
				
				
				
				
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
