package com.liuxubin.start;

import com.liuxubin.operator.Operator;
import com.liuxubin.operator.OperatorImpl;
import com.liuxubin.util.MyDate;
/**
 * @������ ������
 * @��� ϵͳ��������
 * @�������� 2020-07-21
 * @�������� 2020-07-29
 * @�汾 v1.0
 * @˵�� ϵͳ��Ψһ���
 *
 */
public class StartApp {
	/**
	 * ϵͳ���������
	 * @param args
	 */

	public static void main(String[] args) {
		//1.���ݳ�ʼ��
		MyDate mydate=new MyDate();
		//2.��Ŀ����
		Operator operator=new OperatorImpl();
		operator.start();
	}

}
