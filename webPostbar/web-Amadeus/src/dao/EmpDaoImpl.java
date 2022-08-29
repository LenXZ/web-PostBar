package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import dao.EmpDao;
import entity.Emp;
/**
 * EmpDao��ʵ����
 * **/
public class EmpDaoImpl implements EmpDao{
	public List<Emp> findAll() {
	
		String sqlString="select * from emp";
		List<Emp> list=new ArrayList<Emp>();
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				String idString=resultSet.getString("ID");
				String nameString=resultSet.getString("user");
				String genderString=resultSet.getString("gender");
				int salary=resultSet.getInt("salary");
				Emp user=new Emp(idString, nameString, genderString, salary);
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//����û�
	public void addEmp(Emp emp) {
		String sqlString="insert into emp values (?,?,?,?)";
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, emp.getIdString());
			preparedStatement.setString(2, emp.getNameString());
			preparedStatement.setString(3, emp.getGenderString());
			preparedStatement.setInt(4, emp.getSalary());
			preparedStatement.executeUpdate();
			System.out.println("����ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//ɾ���û�
	public void deleteEmp(String idString) {
		String sqlString="delete from emp where id=?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sqlString);	
			preparedStatement.setString(1, idString);
			preparedStatement.executeUpdate();
			System.out.println("ɾ���ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//����ID��ѯ�û�
	public Emp findById(String idString) {
		String sqlString="select * from emp where id=?";
		Emp emp = null;
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, idString);
			ResultSet rSet=preparedStatement.executeQuery();
			while(rSet.next()){
				String nameString=rSet.getString("user");
				String gender=rSet.getString("gender");
				int salary=rSet.getInt("salary");
				emp=new Emp(idString, nameString, gender, salary);
				
			}
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	//�޸��û�
	public void modifyEmp(Emp emp) {
		String sqlString="update emp set user=?,gender=?,salary=? where id=?";
		try {
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setString(1, emp.getNameString());
			preparedStatement.setString(2, emp.getGenderString());
			preparedStatement.setInt(3, emp.getSalary());
			preparedStatement.setString(4, emp.getIdString());
			preparedStatement.executeUpdate();
			System.out.println("�޸ĳɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpDaoImpl empDaoImpl=new EmpDaoImpl();
//		empDaoImpl.modifyEmp(new Emp("001","116","Ů",100));
		System.out.println(empDaoImpl.findById("001"));
	}
	
}
