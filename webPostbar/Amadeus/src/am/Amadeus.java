package am;
import java.sql.*;
import java.sql.DriverManager;;

public class Amadeus {

	public static void main(String[] args)throws Exception {//�׳��쳣
		
		//�������ݿ�����
		Class.forName("com.mysql.jdbc.Driver");
		//������Դ·��
		String url="jdbc:mysql://localhost:3306/Amadeus";
		//�û���
		String user="root";
		//����
		String pwd="root";
		//��ȡ����
		Connection conn=DriverManager.getConnection(url,user,pwd);
		System.out.println(conn);
		//��������ָ��
		Statement sta=conn.createStatement();
		//��ȡ�����
		ResultSet rs=sta.executeQuery("select * from lab ");
		for(int id=1;rs.next();){//ѯ���Ƿ�����һ��Ԫ��
			String name=rs.getString("name");
			int age=rs.getInt("age");
			System.out.println(id+++"\t"+name+"\t"+age);
		}
		//���ѯ�޹ص���update������ֵ����������ʾӰ���˼�������
//		sta.executeUpdate("insert into lab(name,age) values ('moe',22)");
		sta.executeUpdate("update lab set name='rintaro' where id=1");
		//execute&executeUpdate����Ч
		int influence=sta.executeUpdate("alter table lab modify sex int;");
		System.out.println(influence);
	}

}
