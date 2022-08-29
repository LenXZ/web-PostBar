package web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dao.*;
import entity.*;
public class Action01 extends HttpServlet {

	/**
		������һ����service 		
		http://localhost:8080/PostBar/login.jsp
	 */
	User my;
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
		
		//�û�����
		if(action.equals("list")){
			System.out.println(action);
			UserDao dao=new UserDaoImpl();
			List<User> list=dao.findAll();
			System.out.println(list);
			//�����ݵ�request�� ���� ������
			request.setAttribute("list", list);
			//ת��
			request.getRequestDispatcher("list.jsp").forward(request, response);
			
		}else if (action.equals("adminadd")) {
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String pwdString=request.getParameter("pwdString");
			String genderString=request.getParameter("genderString");
			String authority=request.getParameter("authority");
			User user=new User(idString, nameString, pwdString, genderString, authority);
			UserDao dao=new UserDaoImpl();
			dao.addUser(user);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//�ض���
			response.sendRedirect("list.do");
		
		}else if (action.equals("register")) {
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String pwdString=request.getParameter("pwdString");
			String genderString=request.getParameter("genderString");
			String authority="B";
			User user=new User(idString, nameString, pwdString, genderString, authority);
			UserDao dao=new UserDaoImpl();
			try {
				dao.addUser(user);
				request.setAttribute("login","ע��ɹ�");
			} catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("login", "ע��ʧ��");
			}
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//�ض���			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		
		}else if(action.equals("adminload")){
			//��ȡid 
			String idString=request.getParameter("idString");
			//����dao����
			UserDao dao=new	UserDaoImpl();
			User user=dao.findById(idString);
			request.setAttribute("user", user);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			//�ض���
//			response.sendRedirect("list.do");
		}else if(action.equals("update")){
			//��ȡid Integer.parseInt()
			String idString=request.getParameter("idString");
			String nameString=request.getParameter("nameString");
			String pwdString=request.getParameter("pwdString");
			String genderString=request.getParameter("genderString");
			String authority=request.getParameter("authority");
			//����dao����
			UserDao dao=new UserDaoImpl();
			User user=new User(idString, nameString, pwdString, genderString, authority);
			System.out.println(user);
			//ִ��sqlɾ��Ԫ��
			dao.modifyUser(user);
			dao.findById(idString);
			//�ض���
			response.sendRedirect("list.do");
		}else if(action.equals("admindelete")){
			//��ȡid Integer.parseInt()
			String idString=request.getParameter("idString");
			//����dao����
			UserDao dao=new UserDaoImpl();
			//ִ��sqlɾ��Ԫ��
			dao.deleteUser(idString);
			//�ض���
			response.sendRedirect("list.do");
		}
		//��¼
		else if (action.equals("login")) {			
			String myid=request.getParameter("idString");
			String pwdString=request.getParameter("pwdString");
			UserDao dao=new	UserDaoImpl();
			my=dao.findById(myid);
			if(my==null){
//				System.out.println("A3"+myid+pwdString);
				
			}else if (pwdString.equals(my.getPwdString())) {
				//ת��
				response.sendRedirect("PostBar.jsp");
			}else{
//				System.out.println("A0");				
			}
		}
		//���ӹ���
		//
		else if(action.equals("post")){
			//������Ŀ��ʾ����
			//��ȡid 
			String titleString=request.getParameter("idString");
//			System.out.println(titleString);
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findPost(titleString);
//			System.out.println(list);
			//�����ݵ�request�� ���� ������
			request.setAttribute("post", list);
			//ת��
			request.getRequestDispatcher("post.jsp").forward(request, response);
			
		}
		//��ʾ��������
		else if(action.equals("postAll")){
			//��ʾ��������
//			System.out.println(action);
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findAll();
//			System.out.println(list);
			//�����ݵ�request�� ���� ������
			request.setAttribute("postAll", list);
			//ת��
			request.getRequestDispatcher("postAll.jsp").forward(request, response);
			
		}
		//�������**
		else if (action.equals("postaddtrans")) {
			String titleString=request.getParameter("titleString");
			//����dao����
			request.setAttribute("titleString", titleString);
			request.getRequestDispatcher("postadd.jsp").forward(request, response);
		}
		else if (action.equals("postadd")) {
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss");
			String dateString=simpleDateFormat.format(date);		
			String idString=request.getParameter("idString");
//			System.out.print("oo"+idString);
			//������idβ��+1
			int id=Integer.parseInt(idString.substring(6));
			id++;
			idString=idString.substring(0,6).concat(String.valueOf(id));
			System.out.print(idString);
			String textString=request.getParameter("text");
			PostDao dao=new PostDaoImpl();
			Post post=new Post(idString, my.getIdString(), dateString, textString);
			dao.addPost(post);
			request.setAttribute("idString", idString);
			request.getRequestDispatcher("post.do").forward(request, response);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//�ض���
//			response.sendRedirect("post.do");
		}else if (action.equals("postdelete")) {
			String idString=request.getParameter("idString");
			//����dao����
			PostDao dao=new PostDaoImpl();
			//ִ��sqlɾ��Ԫ��
			dao.deletePost(idString);
			request.setAttribute("idString", idString);
			request.getRequestDispatcher("post.do").forward(request, response);
		}else if (action.equals("postupdate")) {			
			//��ȡid Integer.parseInt()
			String idString=request.getParameter("idString");
			PostDao dao=new PostDaoImpl();
			Post post=dao.findById(idString);
			String textString=request.getParameter("text");
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss");
			String dateString=simpleDateFormat.format(date);	
			dao.modifyPost(new Post(post.getIdString(), post.getUserString(), dateString, textString));
			request.setAttribute("idString", idString);
			request.getRequestDispatcher("post.do").forward(request, response);
		}else if (action.equals("titleupdate")) {			
			//��ȡid Integer.parseInt()
			String idString=request.getParameter("idString");
			PostDao dao=new PostDaoImpl();
			Post post=dao.findById(idString);
			String textString=request.getParameter("text");
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss");
			String dateString=simpleDateFormat.format(date);	
			dao.modifyPost(new Post(post.getIdString(), post.getUserString(), dateString, textString));
			//�ض���
			String blockString=idString.substring(0,1);
			if(blockString.equals("A")){
				response.sendRedirect("block1.do");				
			}else if(blockString.equals("B")){
				response.sendRedirect("block2.do");				
			}else if(blockString.equals("C")){
				response.sendRedirect("block3.do");				
			}else if(blockString.equals("D")){
				response.sendRedirect("block4.do");				
			}else if(blockString.equals("E")){
				response.sendRedirect("block5.do");				
			}
		}
		//����id��ʾһ������ ���ڱ༭
		else if (action.equals("postload")||action.equals("titleload")) {	
			//��ȡid 
			String idString=request.getParameter("idString");
			//����dao����
			PostDao dao=new	PostDaoImpl();
			Post post=dao.findById(idString);
			request.setAttribute("post", post);
			request.getRequestDispatcher("postupdate.jsp").forward(request, response);
		}
		//��Ŀ����
		else if (action.equals("postaddtitle")) {		
			String titleString=request.getParameter("titleString");
			//����dao����
			request.setAttribute("titleString", titleString);
			request.getRequestDispatcher("titleadd.jsp").forward(request, response);
		}else if (action.equals("titleadd")) {
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd. hh:mm:ss");
			String dateString=simpleDateFormat.format(date);		
			String idString=request.getParameter("titleString");
			String blockString=idString.substring(0,1);
			//������idβ��+1
			int id=Integer.parseInt(idString.substring(1));
			id++;
			idString=blockString.concat(String.valueOf(id)).concat("100000");
			String textString=request.getParameter("text");
			PostDao dao=new PostDaoImpl();
			Post post=new Post(idString, my.getIdString(), dateString, textString);
			dao.addPost(post);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
			//�ض���
			if(blockString.equals("A")){
				response.sendRedirect("block1.do");				
			}else if(blockString.equals("B")){
				response.sendRedirect("block2.do");				
			}else if(blockString.equals("C")){
				response.sendRedirect("block3.do");				
			}else if(blockString.equals("D")){
				response.sendRedirect("block4.do");				
			}else if(blockString.equals("E")){
				response.sendRedirect("block5.do");				
			}
		}else if (action.equals("titledelete")) {
			String idString=request.getParameter("idString");
			idString=idString.substring(0,6);
			String blockString=idString.substring(0,1);
			PostDao dao=new PostDaoImpl();
			dao.deleteTitle(idString);
			//�ض���
			if(blockString.equals("A")){
				response.sendRedirect("block1.do");				
			}else if(blockString.equals("B")){
				response.sendRedirect("block2.do");				
			}else if(blockString.equals("C")){
				response.sendRedirect("block3.do");				
			}else if(blockString.equals("D")){
				response.sendRedirect("block4.do");				
			}else if(blockString.equals("E")){
				response.sendRedirect("block5.do");				
			}
		}
		else if(action.equals("findTitle")){
			String idString=request.getParameter("titleString");
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(idString);
			try {
				request.setAttribute("block", list);
				//ת��
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		//ת���������
		else if (action.equals("block1")) {	
			//��ȡid 
			String titleString="A";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			try {
				request.setAttribute("block", list);
				//ת��
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		else if (action.equals("block2")) {	
			//��ȡid 
			String titleString="B";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			try {
				request.setAttribute("block", list);
				//ת��
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		else if (action.equals("block3")) {	
			//��ȡid 
			String titleString="C";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			try {
				request.setAttribute("block", list);
				//ת��
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		else if (action.equals("block4")) {	
			//��ȡid 
			String titleString="D";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			try {
				request.setAttribute("block", list);
				//ת��
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}else if (action.equals("block5")) {	
			//��ȡid 
			String titleString="E";
			PostDao dao=new PostDaoImpl();
			List<Post> list=dao.findTitle(titleString);
			System.out.println(list);
			//�����ݵ�request�� ���� ������
			try {
				request.setAttribute("block", list);
				//ת��
				request.getRequestDispatcher("block.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
			
		}
		
	}

}
