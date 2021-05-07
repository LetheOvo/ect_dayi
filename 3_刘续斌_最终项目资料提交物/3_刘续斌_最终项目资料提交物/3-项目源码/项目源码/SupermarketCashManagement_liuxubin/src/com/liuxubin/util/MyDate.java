package com.liuxubin.util;

import com.liuxubin.bean.Goods;
import com.liuxubin.bean.Member;
import com.liuxubin.bean.User;
/**
 * @开发者 刘续斌
 * @类别 系统的数据工具类，用于数据的封装
 * @开始日期 2020-07-21
 * @结束日期 2020-07-29
 * @版本 v1.0
 * @说明 用数组进行数据的初始化
 * @author 80580
 *
 */
public class MyDate {
	/**
	 * userArray类静态变量--用于封装应用的用户信息
	 */
	public static User userArray[];
	/**
	 * goodsArray类静态变量--用于封装应用的商品信息
	 */
	public static Goods goodsArray[];
	/**
	 * cartArray类静态变量--用于封装购买的的商品信息
	 */
	public static Goods cartArray[];
	/**
	 * memberArray类的静态变量--用于封装应用的会员信息
	 */
	public static Member memberArray[];

	/**
	 * MyDate的无参构造方法
	 */
	public MyDate() {
		// 数据的初始化
		initDate();
	}

	/**
	 * 数据的初始化
	 */
	private void initDate() {
		// 1、对用户数组进行初始化
		userArray = new User[10];
		// 1.1 对对象数组赋第一个用户的值
		// 1.1.1 初始化第一个用户对象
		User user1 = new User();
		// 1.1.2 向第一个用户对象各个属性赋值
		user1.setUser_no("1001");
		user1.setUsername("刘续斌");
		user1.setUserpwd("123");
		user1.setUsertype("1");// 1、庫存管理員
		// 1.1.3 将第一个用户对象赋值到数组中
		userArray[0] = user1;
		// 1.2 向对象赋第二个用户的值
		// 1.2.1 初始化第二个用户对象
		User user2 = new User();
		// 1.2.2向第二个用户对象各个属性赋值
		user2.setUser_no("1002");
		user2.setUsername("Lethe");
		user2.setUserpwd("123");
		user2.setUsertype("2");// 2、收銀
		// 1.2.3将第二个用户对象赋值到数组中
		userArray[1] = user2;

		// 1.3向对象赋第3个用户的值
		// 1.3.1 初始化第3个用户对象
		User user3 = new User();
		// 1.3.2向第3个用户对象各个属性赋值
		user3.setUser_no("1003");
		user3.setUsername("逆踏八荒");
		user3.setUserpwd("123");
		user3.setUsertype("0");// 0-无用户类型
		// 1.3.3将第3个用户对象赋值到数组中
		userArray[2] = user3;

		goodsArray = new Goods[50];

		Goods goods1 = new Goods();
		goods1.setGoods_no("1003");
		goods1.setGoods_name("脉动水蜜桃");
		goods1.setGoods_price(7.0);
		goods1.setGoods_unit("1.51L");
		goods1.setGoods_num(50);
		goodsArray[0] = goods1;

		Goods goods2 = new Goods();
		goods2.setGoods_no("1004");
		goods2.setGoods_name("旺仔苹果Q糖");
		goods2.setGoods_price(2.5);
		goods2.setGoods_unit("70g");
		goods2.setGoods_num(70);
		goodsArray[1] = goods2;

		Goods goods3 = new Goods();
		goods3.setGoods_no("1001");
		goods3.setGoods_name("吉百芝麻油");
		goods3.setGoods_price(9.5);
		goods3.setGoods_unit("125ml");
		goods3.setGoods_num(20);
		goodsArray[2] = goods3;

		Goods goods4 = new Goods();
		goods4.setGoods_no("1002");
		goods4.setGoods_name("雀巢奶香咖啡");
		goods4.setGoods_price(1.5);
		goods4.setGoods_unit("13g");
		goods4.setGoods_num(200);
		goodsArray[3] = goods4;

		Goods goods5 = new Goods();
		goods5.setGoods_no("1007");
		goods5.setGoods_name("白玉内酯豆腐");
		goods5.setGoods_price(1.0);
		goods5.setGoods_unit("400g");
		goods5.setGoods_num(10);
		goodsArray[4] = goods5;

		Goods goods6 = new Goods();
		goods6.setGoods_no("1008");
		goods6.setGoods_name("吉百芝麻油");
		goods6.setGoods_price(9.5);
		goods6.setGoods_unit("125ml");
		goods6.setGoods_num(20);
		goodsArray[5] = goods6;

		Goods goods7 = new Goods();
		goods7.setGoods_no("1005");
		goods7.setGoods_name("桃李熟切片");
		goods7.setGoods_price(6.5);
		goods7.setGoods_unit("400g");
		goods7.setGoods_num(10);
		goodsArray[6] = goods7;

		Goods goods8 = new Goods();
		goods8.setGoods_no("1006");
		goods8.setGoods_name("白玉黄豆芽");
		goods8.setGoods_price(2.4);
		goods8.setGoods_unit("350g");
		goods8.setGoods_num(20);
		goodsArray[7] = goods8;

		Goods goods9 = new Goods();
		goods9.setGoods_no("1314");
		goods9.setGoods_name("万家灯火（剑三）");
		goods9.setGoods_price(1314);
		goods9.setGoods_unit("520g");
		goods9.setGoods_num(1314);
		goodsArray[8] = goods9;

		Goods goods10 = new Goods();
		goods10.setGoods_no("1315");
		goods10.setGoods_name("海誓山盟（剑三）");
		goods10.setGoods_price(1314);
		goods10.setGoods_unit("520g");
		goods10.setGoods_num(1314);
		goodsArray[9] = goods10;

		Goods goods11 = new Goods();
		goods11.setGoods_no("1316");
		goods11.setGoods_name("矢志不渝（剑三）");
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
