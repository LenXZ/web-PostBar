package entity;

import java.util.ArrayList;
import java.util.List;

public class test {

	/**
	 * ������
	 */
	public static void main(String[] args) {
		String[] idString={"001","002","003"};
		String[] nameString={"�Բ���̫��","������","������"};
		String genderString="��";
		int salary=10;
//		user user=new user(idString[i],nameString[i],genderString,salary);
//		for(int i=0;i<3;i++){
//			user=new user(idString[i],nameString[i],genderString,salary);
//		}
//		user user1=new user(idString[1],nameString[1],genderString,salary);
//		user user2=new user(idString[2],nameString[2],genderString,salary);
//		user user0=new user(idString[0],nameString[0],genderString,salary);
		//Ԫ��Ϊuser�ķ���
		List<User> list=new ArrayList<User>();
		//���user
//		list.add(user1);
//		list.add(user2);
//		list.add(user0);
		//����user��toString()�������
		System.out.println(list);
		//����list����������ѭ��
		for(int i=0;i<list.size();i++){
			
		}
//		user.setNameString("dd");
//		System.out.println(user.getNameString());
//		System.out.println(user.toString());

	}

}
