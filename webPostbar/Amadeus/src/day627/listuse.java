package day627;

import java.util.ArrayList;
import java.util.List;
/**
 * ���� �涨����ֻ����ʹ��һ�����͵�����
 **/

public class listuse {
	public static void main(String[] args) {
		//��������������
		List list=new ArrayList();//�������� 
		//����ֻ���String��������
		List<String> listStrings=new ArrayList<String>();
		for(int i=0;i<=10;i++){
			listStrings.add(""+(int)(Math.random()*100));			
		}
		System.out.println(listStrings);
	}
}
