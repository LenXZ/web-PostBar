package am;

import java.sql.*;
import java.util.Scanner;

import day627.stringuse;

public class login {


	public static void main(String[] args) throws Exception{
		//�������ݿ�����
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ����
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/amadeus","root","root");
		//��������ָ��
//		Statement sta =conn.createStatement();		
		Scanner scanner=new Scanner(System.in);//new ������Ĺ����� ��Ĺ��������ڳ�ʼ������ ���Լ��������
//		scanner.nextLine();//��ȡ��ǰ����̨��һ�е��ַ���
		System.out.println("������ID���û���");
		//������̨�е�һ���ַ���ת��ΪuserString
		String userString=scanner.nextLine();
		System.out.println("����������");
		//������̨�е�һ���ַ���ת��ΪpwdString
		String pwdString=scanner.nextLine();
		String sql="select * from user where id=? or user=?";
		//Ԥ�������ָ�� �������ٶȿ�
		PreparedStatement preparedStatement=conn.prepareStatement(sql);
		//�ڵ�һ���ʺŴ�����userString
		preparedStatement.setString(1, userString);
		preparedStatement.setString(2, userString);
		//��ȡ�����
		ResultSet rs=preparedStatement.executeQuery();
		if (rs.next()) {
			//�ж������Ƿ����
			if(rs.getString("pwd").equals(pwdString)){
				String gender="";
				//����sex�е�����ȷ���Ա� 0ΪŮ1Ϊ��
				if(rs.getInt("sex")==0){
					gender="Ůʿ";
				}else {
					gender="����";
				}
				System.out.println("��ӭ"+rs.getString("user")+gender+"��½");
			}else{
				System.out.println("�������");
			}
						
		}else{
			System.out.println("�޴��û�");
		}
		
	}

}
