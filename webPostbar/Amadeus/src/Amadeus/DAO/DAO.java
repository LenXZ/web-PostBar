package Amadeus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EmptyBorder;

import com.sun.org.apache.regexp.internal.recompile;

import Amadeus.util.DBUtil;
import entity.User;

/**
 * ���ݿ����*/
public class DAO {
	public DAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	//��ѯ�����û�����Ϣ
	public List<User> findAllUsers(){
		String sqlString="select * from user";
		List<User> list=new ArrayList<User>();
		try {
			//�������ݿ�
			Connection connection=DBUtil.getConnection();
			//ʹ��Ԥ������в�ѯ
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			ResultSet rSet=preparedStatement.executeQuery();
			while (rSet.next()) {
				String idString=rSet.getString("id");
				String nameString=rSet.getString("user");//�û���
				String pwdString=rSet.getString("pwd");
				pwdString="****";
				int gender=rSet.getInt("gender");//�Ա�
				int salary=rSet.getInt("salary");//����
				User user=new User(idString, nameString,pwdString, gender, salary);
				//��list������û�
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			//��ӡ��ջ��Ϣ
			e.printStackTrace();
		}
		return null;
	}	
	//�����û�
	public void addUser(User user){
		String sqlString="insert into user values (?,?,?,?,?)";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user.getIdString());
			preparedStatement.setString(2, user.getNameString());
			preparedStatement.setString(3, user.getPwdString());
			preparedStatement.setInt(4, user.getGender());
			preparedStatement.setInt(5, user.getSalary());
			preparedStatement.executeUpdate();
			System.out.println("����ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//ɾ���û�
	public void deleteUser(String idString){
		String sqlString="delete from user where id=?";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, idString);
			preparedStatement.executeUpdate();
			System.out.println("ɾ���ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//����id�����û�
	public User findById(String idString){
		String sqlString="select * from user where id=?";
		User user = null;
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, idString);
			ResultSet rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				String nameString=rSet.getString("user");
				int gender=rSet.getInt("gender");
				int salary=rSet.getInt("salary");
				user=new User(idString, nameString, "****", gender, salary);
				
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	//�޸��û���Ϣ
	public void modifyUser(User user){
		String sqlString="update user set user=?,gender=?,salary=? where id=?";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, user.getNameString());
			preparedStatement.setInt(2, user.getGender());
			preparedStatement.setInt(3, user.getSalary());
			preparedStatement.setString(4, user.getIdString());
			preparedStatement.executeUpdate();
			System.out.println("�޸ĳɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DAO dao=new DAO();
		User user=new User("000", "Lab","000", 1, 13000);
//		dao.addUser(user);
//		dao.deleteUser("000");
//		System.out.println(dao.findById("002"));
//		System.out.println(dao.findAl());
		dao.modifyUser(user);
	}
}
