package com.liuxubin.util;

import com.liuxubin.bean.Goods;
import com.liuxubin.bean.Member;
import com.liuxubin.bean.User;
/**
 * @������ ������
 * @��� ϵͳ�����ݹ����࣬�������ݵķ�װ
 * @��ʼ���� 2020-07-21
 * @�������� 2020-07-29
 * @�汾 v1.0
 * @˵�� ������������ݵĳ�ʼ��
 * @author 80580
 *
 */
public class MyDate {
	/**
	 * userArray�ྲ̬����--���ڷ�װӦ�õ��û���Ϣ
	 */
	public static User userArray[];
	/**
	 * goodsArray�ྲ̬����--���ڷ�װӦ�õ���Ʒ��Ϣ
	 */
	public static Goods goodsArray[];
	/**
	 * cartArray�ྲ̬����--���ڷ�װ����ĵ���Ʒ��Ϣ
	 */
	public static Goods cartArray[];
	/**
	 * memberArray��ľ�̬����--���ڷ�װӦ�õĻ�Ա��Ϣ
	 */
	public static Member memberArray[];

	/**
	 * MyDate���޲ι��췽��
	 */
	public MyDate() {
		// ���ݵĳ�ʼ��
		initDate();
	}

	/**
	 * ���ݵĳ�ʼ��
	 */
	private void initDate() {
		// 1�����û�������г�ʼ��
		userArray = new User[10];
		// 1.1 �Զ������鸳��һ���û���ֵ
		// 1.1.1 ��ʼ����һ���û�����
		User user1 = new User();
		// 1.1.2 ���һ���û�����������Ը�ֵ
		user1.setUser_no("1001");
		user1.setUsername("������");
		user1.setUserpwd("123");
		user1.setUsertype("1");// 1��������T
		// 1.1.3 ����һ���û�����ֵ��������
		userArray[0] = user1;
		// 1.2 ����󸳵ڶ����û���ֵ
		// 1.2.1 ��ʼ���ڶ����û�����
		User user2 = new User();
		// 1.2.2��ڶ����û�����������Ը�ֵ
		user2.setUser_no("1002");
		user2.setUsername("Lethe");
		user2.setUserpwd("123");
		user2.setUsertype("2");// 2�����y
		// 1.2.3���ڶ����û�����ֵ��������
		userArray[1] = user2;

		// 1.3����󸳵�3���û���ֵ
		// 1.3.1 ��ʼ����3���û�����
		User user3 = new User();
		// 1.3.2���3���û�����������Ը�ֵ
		user3.setUser_no("1003");
		user3.setUsername("��̤�˻�");
		user3.setUserpwd("123");
		user3.setUsertype("0");// 0-���û�����
		// 1.3.3����3���û�����ֵ��������
		userArray[2] = user3;

		goodsArray = new Goods[50];

		Goods goods1 = new Goods();
		goods1.setGoods_no("1003");
		goods1.setGoods_name("����ˮ����");
		goods1.setGoods_price(7.0);
		goods1.setGoods_unit("1.51L");
		goods1.setGoods_num(50);
		goodsArray[0] = goods1;

		Goods goods2 = new Goods();
		goods2.setGoods_no("1004");
		goods2.setGoods_name("����ƻ��Q��");
		goods2.setGoods_price(2.5);
		goods2.setGoods_unit("70g");
		goods2.setGoods_num(70);
		goodsArray[1] = goods2;

		Goods goods3 = new Goods();
		goods3.setGoods_no("1001");
		goods3.setGoods_name("����֥����");
		goods3.setGoods_price(9.5);
		goods3.setGoods_unit("125ml");
		goods3.setGoods_num(20);
		goodsArray[2] = goods3;

		Goods goods4 = new Goods();
		goods4.setGoods_no("1002");
		goods4.setGoods_name("ȸ�����㿧��");
		goods4.setGoods_price(1.5);
		goods4.setGoods_unit("13g");
		goods4.setGoods_num(200);
		goodsArray[3] = goods4;

		Goods goods5 = new Goods();
		goods5.setGoods_no("1007");
		goods5.setGoods_name("������������");
		goods5.setGoods_price(1.0);
		goods5.setGoods_unit("400g");
		goods5.setGoods_num(10);
		goodsArray[4] = goods5;

		Goods goods6 = new Goods();
		goods6.setGoods_no("1008");
		goods6.setGoods_name("����֥����");
		goods6.setGoods_price(9.5);
		goods6.setGoods_unit("125ml");
		goods6.setGoods_num(20);
		goodsArray[5] = goods6;

		Goods goods7 = new Goods();
		goods7.setGoods_no("1005");
		goods7.setGoods_name("��������Ƭ");
		goods7.setGoods_price(6.5);
		goods7.setGoods_unit("400g");
		goods7.setGoods_num(10);
		goodsArray[6] = goods7;

		Goods goods8 = new Goods();
		goods8.setGoods_no("1006");
		goods8.setGoods_name("����ƶ�ѿ");
		goods8.setGoods_price(2.4);
		goods8.setGoods_unit("350g");
		goods8.setGoods_num(20);
		goodsArray[7] = goods8;

		Goods goods9 = new Goods();
		goods9.setGoods_no("1314");
		goods9.setGoods_name("��ҵƻ𣨽�����");
		goods9.setGoods_price(1314);
		goods9.setGoods_unit("520g");
		goods9.setGoods_num(1314);
		goodsArray[8] = goods9;

		Goods goods10 = new Goods();
		goods10.setGoods_no("1315");
		goods10.setGoods_name("����ɽ�ˣ�������");
		goods10.setGoods_price(1314);
		goods10.setGoods_unit("520g");
		goods10.setGoods_num(1314);
		goodsArray[9] = goods10;

		Goods goods11 = new Goods();
		goods11.setGoods_no("1316");
		goods11.setGoods_name("ʸ־���壨������");
		goods11.setGoods_price(1314);
		goods11.setGoods_unit("520g");
		goods11.setGoods_num(1314);
		goodsArray[10] = goods11;

		cartArray = new Goods[50];

		memberArray = new Member[10];

		Member member1 = new Member();
		member1.setMember_no("0001");
		member1.setIntegral(10000);
		memberArray[0] = member1;

		Member member2 = new Member();
		member2.setMember_no("0002");
		member2.setIntegral(2000);
		memberArray[1] = member2;
	}

}
