package com.liuxubin.operator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.liuxubin.bean.Goods;
import com.liuxubin.bean.Member;
import com.liuxubin.bean.User;
import com.liuxubin.service.UserService;
import com.liuxubin.service.UserServiceImpl;
import com.liuxubin.util.MyDate;

/**
 * @开发者 刘续斌
 * @类别 控制台的操作类接口的实现类
 * @开发日期 2020-07-21
 * @结束日期 2020-07-29
 * @版本 v1.0
 * @说明 用于封装用户的操作信息-功能的实现
 *
 */
public class OperatorImpl implements Operator {
	private static User login_user = null;

	/**
	 * 系统的启动方法
	 */
	@Override
	public void start() {
		displaySystemGUI();
	}
/**
 * 显示系统主界面
 */
	private void displaySystemGUI() {
		System.out.println("---------------欢迎使用 超市收银系统-------------");
		System.out.println("|              1、登录                                                 | ");
		System.out.println("|              2、退出                                                 |");
		System.out.println("---------------请选择输入1/2---------------------");
		boolean Lethe = true;
		do {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String numString = scanner.next();
			switch (numString) {
			case "1":
				showLoginGUI();
				Lethe = false;
				break;
			case "2":
				System.out.println("感谢使用！");
				Lethe = false;
				System.exit(0);// 系统正常退出
				break;
			default:
				System.out.println("请输入1和2");
				break;
			}
		} while (Lethe);
	}
/**
 * 显示登陆功能主界面
 */
	private void showLoginGUI() {
		boolean Lethe = true;
		do {
			// 1、数据接收
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.print("用户名称");
			String username = scanner.next();
			System.out.print("用户密码");
			String userpwd = scanner.next();
			// 2、将数据传递给业务层进行处理
			UserService userService = new UserServiceImpl();
			login_user = userService.loginByUsernameAndUserpwd(username, userpwd);
			if (login_user != null) {
				System.out.println("登陆成功！");
				/**
				 * 主界面的选择
				 */
				// System.out.println(user.getUsername());
				if (login_user.getUsertype() != null && login_user.getUsertype().equals("1")) {
					storeManageGUI();
					Lethe = false;
				} else if (login_user.getUsertype() != null && login_user.getUsertype().equals("2")) {
					cashManageGUI();
					Lethe = false;
				} else {
					System.out.println("角色未分配");
				}
			} else {
				System.out.println("登陆失败，请重新登录！");
				Lethe = true;
			}
		} while (Lethe);
	}

	/**
	 * 显示收银管理的主界面
	 */
	private void cashManageGUI() {
		System.out.println("*******************欢迎登录  收银管理系统*******************");
		System.out.println("请选择进行的操作：1、扫描商品 2、修改数量 3、结账 4、退出5、显示购物车");
		boolean Lethe = true;
		do {
			Scanner scanner = new Scanner(System.in);
			String numString = scanner.next();
			switch (numString) {
			case "1":
				scanProduct();// 扫描商品
				Lethe = false;
				break;
			case "2":
				modifiedQuantity();// 修改数量
				Lethe = false;
				break;
			case "3":
				checkOut();// 结账
				Lethe = false;
				break;
			case "4":
				displaySystemGUI();//退出
				break;
			case "5":
				showCartArray();// 显示购物车
				Lethe = false;
				break;

			default:
				System.out.println("请输入1-4的值");
				break;

			}
		} while (Lethe);

	}

	private void scanProduct() {
		// 1接收要扫描的商品编号
		System.out.print("输入要扫描的商品编号：");
		Scanner scanner = new Scanner(System.in);
		String goods_no = scanner.next();
		// 2判断该商品编号是否存在
		boolean flag = false;
		for (int i = 0; i < MyDate.goodsArray.length; i++) {
			Goods temp_goods = MyDate.goodsArray[i];
			if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
				flag = true;
				break;
			}
		}

		if (flag) {
			//2.1如果存在商品编号，调整购物车
			// 用于判断购物车中是否有此商品
			boolean result = false;
			for (int i = 0; i < MyDate.cartArray.length; i++) {
				Goods cart_goods = MyDate.cartArray[i];
				if (cart_goods != null && cart_goods.getGoods_no().equals(goods_no)) {
					result = true;
					break;
				}
			}
			// 根据判断结果进行购物车中信息的调整
			// true--数量累加操作
			// false--商品添加操作
			Goods cart_goods = null;
			if (result) {
				//2.1.1购物车中有该编号的商品信息，调整数量
				boolean Lethe = false;
				for (int i = 0; i < MyDate.cartArray.length; i++) {
					Goods temp_cart_goods = MyDate.cartArray[i];
					if (temp_cart_goods != null && temp_cart_goods.getGoods_no().equals(goods_no)) {
						temp_cart_goods.setGoods_num(temp_cart_goods.getGoods_num() + 1);
						MyDate.cartArray[i] = temp_cart_goods;
						cart_goods = MyDate.cartArray[i];
						Lethe = true;
						break;
					}
				}
				if (Lethe) {
					System.out.println("添加成功");
				} else {
					System.out.println("添加失败");
				}
			} else {
				// new_cart_goods新增购物车商品对象
				//2.1.2购物车中没有该编号的商品信息，添加新的购物车商品信息
				Goods new_cart_goods = null;
				// 查找该商品编号的所有商品信息，并将库存数量调整成购买数量
				for (int i = 0; i < MyDate.goodsArray.length; i++) {
					Goods temp_goods = MyDate.goodsArray[i];
					if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
						temp_goods.setGoods_num(1);
						new_cart_goods = temp_goods;
						break;
					}

				}
				// 将新增购物车商品对象添加到购物车的数组中去
				boolean remark = false;
				if (new_cart_goods != null) {
					for (int i = 0; i < MyDate.cartArray.length; i++) {
						if (MyDate.cartArray[i] == null) {
							MyDate.cartArray[i] = new_cart_goods;
							cart_goods = MyDate.cartArray[i];
							remark = true;
							break;
						}
					}
				}
				if (remark) {
					System.out.println("添加成功");
				} else {
					System.out.println("添加失败");
				}
			}
			if (cart_goods != null) {
				System.out.println("商品名称\t\t\t单价\t数量\t金额");
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + cart_goods.getGoods_no() + ")" + cart_goods.getGoods_name()
						+ cart_goods.getGoods_unit() + "\t" + cart_goods.getGoods_price() + "\t"
						+ cart_goods.getGoods_num() + "\t" + (cart_goods.getGoods_price() * cart_goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
		} else {
			//2.2不存在，提示
			System.out.println("对不起，没有此商品，扫描失败！！！");
		}
		// 3切换回收银主菜单界面
		cashManageGUI();
	}

	private void modifiedQuantity() {
		// 1、判断购物车数组中是否有对象
		boolean flag = false;
		for (int i = 0; i < MyDate.cartArray.length; i++) {
			if (MyDate.cartArray[i] != null) {
				flag = true;
				break;
			}
		}
		if (flag == true) {
			// 2.1接收商品编号
			String goods_no = null;
			boolean isExist = true;
			do {
				Scanner scanner = new Scanner(System.in);
				System.out.print("输入要修改的商品编号：");
				goods_no = scanner.next();
				// 2.2判断购物车中是否有该商品的编号
				boolean remark = false;
				for (int i = 0; i < MyDate.cartArray.length; i++) {
					if (MyDate.cartArray[i] != null && MyDate.cartArray[i].getGoods_no().equals(goods_no)) {
						remark = true;
						break;
					}
				}
				if (remark == false) {
					System.out.println("没有扫描秒此商品");
					isExist = true;
				} else {
					isExist = false;
				}
			} while (isExist);
			//2.2.1接收要修改的数量
			boolean isInteger = true;
			int goods_num = 0;
			do {
				try {
					System.out.println("输入修改数量");
					Scanner scanner = new Scanner(System.in);
					goods_num = scanner.nextInt();
					if (goods_num <= 0) {
						System.out.println("请输入大于0的数字！！！");
						isInteger = true;
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.println("请输入正确的数量！！！");
					isInteger = true;
				}
			} while (isInteger);
			// 2.3调整购物车中的数组中有该商品的数量
			boolean isModified = false;
			Goods cart_goods = null;
			for (int i = 0; i < MyDate.cartArray.length; i++) {
				Goods temp_cart_goods = MyDate.cartArray[i];
				if (temp_cart_goods != null && temp_cart_goods.getGoods_no().equals(goods_no)) {
					temp_cart_goods.setGoods_num(goods_num);
					MyDate.cartArray[i] = temp_cart_goods;
					cart_goods = MyDate.cartArray[i];
					isModified = true;
					break;
				}
			}
			// 2.4显示修改商品数量的状态
			if (isModified) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
			if (cart_goods != null) {
				System.out.println("商品名称\t\t\t单价\t数量\t金额");
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + cart_goods.getGoods_no() + ")" + cart_goods.getGoods_name()
						+ cart_goods.getGoods_unit() + "\t" + cart_goods.getGoods_price() + "\t"
						+ cart_goods.getGoods_num() + "\t" + (cart_goods.getGoods_price() * cart_goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
			//2.5切换到收银主菜单
			cashManageGUI();
		} else {
			System.out.println("购物车中无商品，请选择1.扫描商品");
			cashManageGUI();
		}

	}

	private void showCartArray() {
		Goods CartArray[] = MyDate.cartArray;
		System.out.println("显示购物车中的所有信息");
		for (Goods goods : CartArray) {
			if (goods != null) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("商品名称\t\t\t单价\t数量\t金额");
				if (goods != null) {
					System.out.println("(" + goods.getGoods_no() + ")" + goods.getGoods_name() + goods.getGoods_unit()
							+ "\t" + goods.getGoods_price() + "\t" + goods.getGoods_num() + "\t"
							+ (goods.getGoods_price() * goods.getGoods_num()));
				}
			}
		}
		cashManageGUI();
	}

	private void checkOut() {
		// 1、判断购物车是否为空
		boolean isEmpty = false;
		for (int i = 0; i < MyDate.cartArray.length; i++) {
			if (MyDate.cartArray[i] != null) {
				isEmpty = true;
				break;
			}
		}

		// 2、对购物车不同状态做出响应
		if (isEmpty) {
			// 2.1购物车不为空，计算购物车中总金额
			double sum = 0;
			for (int i = 0; i < MyDate.cartArray.length; i++) {
				Goods temp_cart_goods = MyDate.cartArray[i];
				if (temp_cart_goods != null) {
					double amount = temp_cart_goods.getGoods_price() * temp_cart_goods.getGoods_num();
					sum += amount;
				}
			}
			System.out.println("总金额是：" + sum);
			System.out.println("1、普通结账  2、会员结账");
			do {
				Scanner scanner = new Scanner(System.in);
				String numString = scanner.next();
				switch (numString) {
				case "1":
					generalCheckOut(sum);
					break;

				case "2":
					memberCheckOut(sum);
					break;

				default:
					System.out.println("请选择1或2");
					break;
				}
			} while (true);

		} else {
			// 2.2购物车为空，提示进行其他操作
			System.out.println("购物车为空，请选择1扫描商品！！");
			cashManageGUI();
		}

	}

	private void memberCheckOut(double sum) {
		// 1、接受正确的会员卡号
		String member_no = "";
		Member cuurent_memberMember = null;
		boolean flag = true;
		do {
			String member_noString = null;
			Scanner scanner = new Scanner(System.in);
			System.out.print("请输入您的会员卡号：");
			member_no = scanner.next();
			boolean result = false;
			for (int i = 0; i < MyDate.memberArray.length; i++) {
				if (MyDate.memberArray[i] != null && MyDate.memberArray[i].getMember_no().equals(member_no)) {
					cuurent_memberMember = MyDate.memberArray[i];
					result = true;
					break;
				}
			}

			if (result) {
				break;
			} else {
				System.out.println("会员卡号错误！！！");
				continue;
			}
		} while (flag);
		// 2、接受正确的付款金额
		double money = 0;
		System.out.print("请输入您要支付的金额：");
		do {
			try {
				Scanner scanner = new Scanner(System.in);
				money = scanner.nextDouble();
				if (money < sum) {
					System.out.println("支付金额不足，请重新输入！！！");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("请输入正确格式的金额！！！");
			}
		} while (true);
		// 3、打印购物小票
		System.out.println("                               超市");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String xiaopiaohaoString = simpleDateFormat.format(date);
		System.out.println("收银员号：" + this.login_user.getUser_no() + "      小票号：" + xiaopiaohaoString);
		System.out.println("序号\t商品名称\t\t\t单价\t数量\t金额\t");
		int totalNum = 0;
		for (int i = 0; i < MyDate.cartArray.length; i++) {
			Goods temp_cart_goods = MyDate.cartArray[i];
			if (temp_cart_goods != null) {
				String goods_name = "(" + temp_cart_goods.getGoods_no() + ")" + temp_cart_goods.getGoods_name()
						+ temp_cart_goods.getGoods_unit();
				double amount = temp_cart_goods.getGoods_price() * temp_cart_goods.getGoods_num();
				System.out.println((i + 1) + "\t" + goods_name + "\t\t\t" + temp_cart_goods.getGoods_price() + "\t"
						+ temp_cart_goods.getGoods_num() + "\t" + amount + "\t");
				totalNum += temp_cart_goods.getGoods_num();
			}
		}
		System.out.println("----------------------------------------------------------------------");
		System.out.println("总数量：" + totalNum + "\t 应收：" + sum);
		System.out.println("实收：" + money + "\t找零：" + (money - sum));
		System.out.println("本次累计积分成功      卡号：" + member_no);
		System.out.println("消费前积分+本次消费积分=消费后积分");
		int thisIntegral = (int) sum;// 本次积分
		int afterConsumptionIntegral = cuurent_memberMember.getIntegral() + (int) sum;
		System.out.println(cuurent_memberMember.getIntegral() + "+" + thisIntegral + "=" + afterConsumptionIntegral);
		// 调整会员数组中的积分
		for (int i = 0; i < MyDate.memberArray.length; i++) {
			Member temp_member = MyDate.memberArray[i];
			if (temp_member != null && temp_member.getMember_no().equals(member_no)) {
				temp_member.setIntegral(afterConsumptionIntegral);
				break;
			}
		}
		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/    HH:mm:ss");
		String dateString = simpleDateFormat.format(date);
		System.out.println(dateString);
		System.out.println("请妥善保管小票，以备不时之需");
		System.out.println("=======================================================================");
		MyDate.cartArray = new Goods[50];
		cashManageGUI();

	}

	private void generalCheckOut(double sum) {
		System.out.print("请输入您要支付的金额：");
		double money = 0;
		do {
			try {
				Scanner scanner = new Scanner(System.in);
				money = scanner.nextDouble();
				if (money < sum) {
					System.out.println("支付金额不足，请重新输入！！！");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("请输入正确格式的金额！！！");
			}
		} while (true);

		System.out.println("                               超市");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String xiaopiaohaoString = simpleDateFormat.format(date);
		System.out.println("收银员号：" + this.login_user.getUser_no() + "      小票号：" + xiaopiaohaoString);
		System.out.println("序号\t商品名称\t\t\t单价\t数量\t金额\t");
		int totalNum = 0;
		for (int i = 0; i < MyDate.cartArray.length; i++) {
			Goods temp_cart_goods = MyDate.cartArray[i];
			if (temp_cart_goods != null) {
				String goods_name = "(" + temp_cart_goods.getGoods_no() + ")" + temp_cart_goods.getGoods_name()
						+ temp_cart_goods.getGoods_unit();
				double amount = temp_cart_goods.getGoods_price() * temp_cart_goods.getGoods_num();
				System.out.println((i + 1) + "\t" + goods_name + "\t\t\t" + temp_cart_goods.getGoods_price() + "\t"
						+ temp_cart_goods.getGoods_num() + "\t" + amount + "\t");
				totalNum += temp_cart_goods.getGoods_num();
			}
		}
		System.out.println("----------------------------------------------------------------------");
		System.out.println("总数量：" + totalNum + "\t 应收：" + sum);
		System.out.println("实收：" + money + "\t找零：" + (money - sum));

		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/    HH:mm:ss");
		String dateString = simpleDateFormat.format(date);
		System.out.println(dateString);
		System.out.println("请妥善保管小票，以备不时之需");
		System.out.println("=======================================================================");
		MyDate.cartArray = new Goods[50];
		cashManageGUI();
	}

	/**
	 * 显示库存管理的主界面
	 */
	private void storeManageGUI() {
		System.out.println("******************欢迎登录  库存管理系统******************");
		System.out.println("请选择进行的操作：1、商品入库 2、商品出库 3、新增商品 4、查询全部商品 5、按查询号查询商品 6、退出");
		boolean Lethe = true;
		do {
			Scanner scanner = new Scanner(System.in);
			String numString = scanner.next();
			switch (numString) {
			case "1":
				goodsWarehousing();// 商品入库
				Lethe = false;
				break;
			case "2":
				goodsWarehousOut();// 商品出库
				Lethe = false;
				break;
			case "3":
				addGoods();// 新增商品
				Lethe = false;
				break;
			case "4":
				queryAllGoods();// 查询全部商品
				Lethe = false;
				break;
			case "5":
				queryProuductsByQueryNumber();// 按查询号查询商品
				Lethe = false;
				break;
			case "6":
				displaySystemGUI();// 退出
				break;
			default:
				System.out.println("请输入1-6的值");
			}
		} while (Lethe);
	}

	/**
	 * 商品入库
	 */
	private void goodsWarehousing() {
		// 1、接受商品编号，并验证是否有该商品
		String goods_no = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("输入商品编号：");
		goods_no = scanner.next();

		boolean aloha = false;
		for (int i = 0; i < MyDate.goodsArray.length; i++) {
			Goods temp_goods = MyDate.goodsArray[i];
			if (temp_goods!= null && temp_goods.getGoods_no().equals(goods_no)) {
				aloha = true;
				break;
			}
		}

		// 2、判断是否有此商品
		if (aloha) {
			// 2.1.1若有，接受数量
			int goods_num = 0;
			do {
				try {
					scanner = new Scanner(System.in);
					System.out.print("输入商品的数量：");
					goods_num = scanner.nextInt();
					if (goods_num < 0) {
						System.out.println("数量必须是大于0的整数");
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.println("请输入正确的数量！！！");
				}
			} while (true);
			// 2.1.2对该商品的数量进行调整
			boolean remark =false;
			for (int i = 0; i < MyDate.goodsArray.length; i++) {
				Goods temp_goods = MyDate.goodsArray[i];
				if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
					temp_goods.setGoods_num(temp_goods.getGoods_num() + goods_num);
					MyDate.goodsArray[i] = temp_goods;
					remark =true;
					break;
				}
			}
			// 2.1.3入库状态——成功OR失败
			if (remark) {
				System.out.println("入库成功");
			} else {
				System.out.println("入库失败");
			}
		} else {
			// 2.2没有此商品，请选择编号
			System.out.println("没有此商品，请选择编号 3:新增商品！！");
		}
		// 3、显示所有商品信息
		// 4、显示库存管理界面
		queryAllGoods();
	}

	/**
	 * 商品出库
	 */
	private void goodsWarehousOut() {
		// 1、接受商品编号，并验证是否有此商品
		Scanner scanner = null;
		String goods_no = "";
		do {
			scanner = new Scanner(System.in);
			System.out.print("输入商品编号：");
			goods_no = scanner.next();
			//2、验证是否有此商品
			boolean Lethe = false;
			for (int i = 0; i < MyDate.goodsArray.length; i++) {
				Goods temp_goods = MyDate.goodsArray[i];
				if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
					Lethe = true;
					break;
				}
			}
			if (Lethe == false) {
				System.out.println("没有此商品！！");
				continue;
			}
			break;
		} while (true);
		int out_num = 0;
		do {
			try {
				System.out.print("出库数量：");
				scanner = new Scanner(System.in);
				out_num = scanner.nextInt();
				if (out_num <= 0) {
					System.out.println("出库数量必须大于0");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("请输入正确的数量");
			}
		} while (true);

		boolean result = false;
		Goods old_goods = null;
		for (int i = 0; i < MyDate.goodsArray.length; i++) {
			Goods temp_goods = MyDate.goodsArray[i];
			if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
				if (temp_goods.getGoods_num() > out_num) {
					temp_goods.setGoods_num(temp_goods.getGoods_num() - out_num);
					result = true;
					break;
				}
				old_goods = temp_goods;
			}
		}
		if (result) {
			System.out.println("出库成功");
			queryAllGoods();
		} else {
			System.out.println("库存不足，请核实，出库失败！！！");
			System.out.println("商品编号\t商品名称\t\t商品单价\t单位\t数量");
			System.out.println(
					old_goods.getGoods_no() + "\t\t" + old_goods.getGoods_name() + "\t\t" + old_goods.getGoods_price()
							+ "\t\t" + old_goods.getGoods_unit() + "\t\t" + old_goods.getGoods_num());
			storeManageGUI();
		}
	}
/**
 * 新增商品
 */
	private void addGoods() {
		Scanner scanner = null;
		// A、获取正确商品编号
		String goods_no = "";
		boolean Lethe = false;
		do {
			// 2、验证
			// 2.1验证编号是否存在
			scanner = new Scanner(System.in);
			System.out.print("请输入商品编号：");
			goods_no = scanner.next();
			boolean remark = true;
			Goods goodsArray[] = MyDate.goodsArray;
			for (int i = 0; i < goodsArray.length; i++) {
				Goods goods = goodsArray[i];
				if (goods != null && goods.getGoods_no().equals(goods_no)) {
					System.out.println("此编号已存在！请重新输入！");
					remark = false;
					break;
				}
			}
			// 2.2验证编号是否存在
			// remark--true 改变好不存在，退出循环
			// remark--false 该编号已存在，继续循环
			if (remark == true) {
				Lethe = false;
			} else {
				Lethe = true;
			}
		} while (Lethe);
		// B、依次获取商品名称、商品价格、商品单位、商品数量，应有必要判断。
		// 1、获取商品名称
		scanner = new Scanner(System.in);
		System.out.print("输入商品名称");
		String goods_name = scanner.next();
		// 2、获取商品价格
		double goods_price = 0;
		Lethe = true;
		do {
			try {
				scanner = new Scanner(System.in);
				System.out.print("输入商品价格：");
				goods_price = scanner.nextDouble();
				Lethe = false;
			} catch (Exception e) {
				System.out.println("请输入正确的价格");
			}
		} while (Lethe);
		// 3、获取商品单位
		System.out.print("输入商品单位");
		String goods_unit = scanner.next();
		// 4、获取商品数量
		int goods_num = 0;
		Lethe = true;
		do {
			try {
				scanner = new Scanner(System.in);
				System.out.print("输入商品数量：");
				goods_num = scanner.nextInt();
				Lethe = false;
			} catch (Exception e) {
				System.out.println("请输入正确的数量");
			}
		} while (Lethe);
		// C、数据的保存：保存有效的商品信息并对保存情况进行反馈。
		// 1、封装
		Goods new_goods = new Goods();// 数组声明
		new_goods.setGoods_no(goods_no);
		new_goods.setGoods_name(goods_name);
		new_goods.setGoods_price(goods_price);
		new_goods.setGoods_unit(goods_unit);
		new_goods.setGoods_num(goods_num);
		// 2、将新增商品对象添加到已有的商品数组中
		boolean result = false;
		for (int i = 0; i < MyDate.goodsArray.length; i++) {
			Goods temp_goodsGoods = MyDate.goodsArray[i];
			if (temp_goodsGoods == null) {
				MyDate.goodsArray[i] = new_goods;
				result = true;
				break;
			}
		}
		// 3、根据是否新增商品成功状态进行必要的提示
		if (result) {
			System.out.println("新增商品成功");
		} else {
			System.out.println("新增商品失败");

		}
		// D、最终显示全部库存商品的商品编号、商品名称、商品价格、商品单位、商品数量

		// E、返回库存主菜单
		queryAllGoods();
	}
/**
 * 查询全部商品
 */
	private void queryAllGoods() {
		Goods goodsArray[] = MyDate.goodsArray;
//		System.out.println("查询全部商品");
		System.out.println("商品编号\t商品名称\t\t单价\t单位\t数量");
		for (Goods goods : goodsArray) {
			if (goods != null) {

				System.out.println(goods.getGoods_no() + "\t\t" + goods.getGoods_name() + "\t\t"
						+ goods.getGoods_price() + "\t\t" + goods.getGoods_unit() + "\t\t" + goods.getGoods_num());
			}
		}
		storeManageGUI();
	}
/**
 * 按查询号查询商品
 */
	private void queryProuductsByQueryNumber() {
		// 1、接受商品编号
		Scanner scanner = new Scanner(System.in);
		System.out.print("输入商品编号");
		String goods_no = scanner.next();
		// 2、比对商品数组，如果存在，输出该商品信息
		Goods goodsArray[] = MyDate.goodsArray;
		boolean Lethe = true;
		for (Goods goods : goodsArray) {

			if (goods != null) {
				if (goods.getGoods_no().equals(goods_no)) {
					System.out.println("商品编号 商品名称 商品单价 单位 数量");
					System.out.println(goods.getGoods_no() + "\t" + goods.getGoods_name() + "\t"
							+ goods.getGoods_price() + "\t" + goods.getGoods_unit() + "\t" + goods.getGoods_num());
					Lethe = false;
					break;
				}
			}
		}
		if (Lethe) {
			System.out.println("对不起，没有此商品！");
		}
		storeManageGUI();

	}
}
