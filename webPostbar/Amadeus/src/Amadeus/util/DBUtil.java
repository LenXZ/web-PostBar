package Amadeus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * ������ ���ڻ�ȡ���ݿ�����
 *  
 */
public class DBUtil {
	
	//public static�������ɵ��õķ���
	public static Connection getConnection(){
		
		try {
			//�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			//��ӡ��ջ��Ϣ
			e.printStackTrace();
		}
		//������Դ·��
		String url="jdbc:mysql://localhost:3306/Amadeus?useUnicode=true&characterEncoding=utf8";
		//�û���
		String user="root";
		//����
		String pwd="root";	
		try {
			//��ȡ����
			Connection conn=DriverManager.getConnection(url,user,pwd);
			//��������
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//������,�򲻷����κ�ֵ
		return null;
	}
/**
	public static void main(String[] args) {
		Connection connection=DBUtil.getConnection();
		System.out.print(connection);
	}*/
}
