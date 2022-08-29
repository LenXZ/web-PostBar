package dao;

import java.util.List;

import entity.Emp;

public interface EmpDao {
	//��ѯ����
	public List<Emp> findAll();
	//����id��ѯ
	public Emp findById(int id);
	//���Ա��
	public void addEmp(Emp emp);
	//ɾ��Ա��
	public void deleteEmp(int id);
	//�޸�Ա��
	public void modifyEmp(Emp e);
}
