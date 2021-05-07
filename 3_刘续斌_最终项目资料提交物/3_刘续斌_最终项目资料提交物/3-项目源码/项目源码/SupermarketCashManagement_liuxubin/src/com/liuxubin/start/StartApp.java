package com.liuxubin.start;

import com.liuxubin.operator.Operator;
import com.liuxubin.operator.OperatorImpl;
import com.liuxubin.util.MyDate;
/**
 * @开发者 刘续斌
 * @类别 系统的启动类
 * @开发日期 2020-07-21
 * @结束日期 2020-07-29
 * @版本 v1.0
 * @说明 系统的唯一入口
 *
 */
public class StartApp {
	/**
	 * 系统启动总入口
	 * @param args
	 */

	public static void main(String[] args) {
		//1.数据初始化
		MyDate mydate=new MyDate();
		//2.项目启动
		Operator operator=new OperatorImpl();
		operator.start();
	}

}
