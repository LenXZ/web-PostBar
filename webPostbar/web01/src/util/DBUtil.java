package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 *��ȡ���ݿ����ӵĹ�����
 */
public class DBUtil {
	public static Connection 
					getConnection(){
		try {
			Class.forName
				("com.mysql.jdbc.Driver");
			String url=
			"jdbc:mysql://localhost:3306/lx" +
			"?useUnicode=true" +
			"&characterEncoding=utf8";
			String user ="root";
			String pwd = "1234";
			Connection conn =
				DriverManager.getConnection
				(url,user,pwd);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//���������Ƿ��ȡ�ɹ�
	public static void main(String[] args) {
		Connection conn= 
			DBUtil.getConnection();
		System.out.println(conn);
	}
	
}
