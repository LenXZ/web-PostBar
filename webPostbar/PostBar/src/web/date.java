package web;

import java.text.SimpleDateFormat;
import java.util.Date;

public class date {

	/**
	 * ����
	 */
	public date() {
		while(true){	
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
			String timeString=simpleDateFormat.format(date);
			try {
				//��˯1000���� ��1�룩
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(timeString);
		}
	}
	public static void main(String[] args) {
/**		Date date=new Date();		
		System.out.println(date);
		//�������ڸ�ʽ
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss E");
		String timeString=simpleDateFormat.format(date);		
		System.out.println(timeString);
*/
		while(true){	
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
			String timeString=simpleDateFormat.format(date);
			try {
				//��˯1000���� ��1�룩
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(timeString);
		}
	}

}
