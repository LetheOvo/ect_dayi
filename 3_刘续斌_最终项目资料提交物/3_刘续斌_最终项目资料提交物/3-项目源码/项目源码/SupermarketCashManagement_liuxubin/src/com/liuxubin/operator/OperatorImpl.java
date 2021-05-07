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
 * @������ ������
 * @��� ����̨�Ĳ�����ӿڵ�ʵ����
 * @�������� 2020-07-21
 * @�������� 2020-07-29
 * @�汾 v1.0
 * @˵�� ���ڷ�װ�û��Ĳ�����Ϣ-���ܵ�ʵ��
 *
 */
public class OperatorImpl implements Operator {
	private static User login_user = null;

	/**
	 * ϵͳ����������
	 */
	@Override
	public void start() {
		displaySystemGUI();
	}
/**
 * ��ʾϵͳ������
 */
	private void displaySystemGUI() {
		System.out.println("---------------��ӭʹ�� ��������ϵͳ-------------");
		System.out.println("|              1����¼                                                 | ");
		System.out.println("|              2���˳�                                                 |");
		System.out.println("---------------��ѡ������1/2---------------------");
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
				System.out.println("��лʹ�ã�");
				Lethe = false;
				System.exit(0);// ϵͳ�����˳�
				break;
			default:
				System.out.println("������1��2");
				break;
			}
		} while (Lethe);
	}
/**
 * ��ʾ��½����������
 */
	private void showLoginGUI() {
		boolean Lethe = true;
		do {
			// 1�����ݽ���
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.print("�û�����");
			String username = scanner.next();
			System.out.print("�û�����");
			String userpwd = scanner.next();
			// 2�������ݴ��ݸ�ҵ�����д���
			UserService userService = new UserServiceImpl();
			login_user = userService.loginByUsernameAndUserpwd(username, userpwd);
			if (login_user != null) {
				System.out.println("��½�ɹ���");
				/**
				 * �������ѡ��
				 */
				// System.out.println(user.getUsername());
				if (login_user.getUsertype() != null && login_user.getUsertype().equals("1")) {
					storeManageGUI();
					Lethe = false;
				} else if (login_user.getUsertype() != null && login_user.getUsertype().equals("2")) {
					cashManageGUI();
					Lethe = false;
				} else {
					System.out.println("��ɫδ����");
				}
			} else {
				System.out.println("��½ʧ�ܣ������µ�¼��");
				Lethe = true;
			}
		} while (Lethe);
	}

	/**
	 * ��ʾ���������������
	 */
	private void cashManageGUI() {
		System.out.println("*******************��ӭ��¼  ��������ϵͳ*******************");
		System.out.println("��ѡ����еĲ�����1��ɨ����Ʒ 2���޸����� 3������ 4���˳�5����ʾ���ﳵ");
		boolean Lethe = true;
		do {
			Scanner scanner = new Scanner(System.in);
			String numString = scanner.next();
			switch (numString) {
			case "1":
				scanProduct();// ɨ����Ʒ
				Lethe = false;
				break;
			case "2":
				modifiedQuantity();// �޸�����
				Lethe = false;
				break;
			case "3":
				checkOut();// ����
				Lethe = false;
				break;
			case "4":
				displaySystemGUI();//�˳�
				break;
			case "5":
				showCartArray();// ��ʾ���ﳵ
				Lethe = false;
				break;

			default:
				System.out.println("������1-4��ֵ");
				break;

			}
		} while (Lethe);

	}

	private void scanProduct() {
		// 1����Ҫɨ�����Ʒ���
		System.out.print("����Ҫɨ�����Ʒ��ţ�");
		Scanner scanner = new Scanner(System.in);
		String goods_no = scanner.next();
		// 2�жϸ���Ʒ����Ƿ����
		boolean flag = false;
		for (int i = 0; i < MyDate.goodsArray.length; i++) {
			Goods temp_goods = MyDate.goodsArray[i];
			if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
				flag = true;
				break;
			}
		}

		if (flag) {
			//2.1���������Ʒ��ţ��������ﳵ
			// �����жϹ��ﳵ���Ƿ��д���Ʒ
			boolean result = false;
			for (int i = 0; i < MyDate.cartArray.length; i++) {
				Goods cart_goods = MyDate.cartArray[i];
				if (cart_goods != null && cart_goods.getGoods_no().equals(goods_no)) {
					result = true;
					break;
				}
			}
			// �����жϽ�����й��ﳵ����Ϣ�ĵ���
			// true--�����ۼӲ���
			// false--��Ʒ��Ӳ���
			Goods cart_goods = null;
			if (result) {
				//2.1.1���ﳵ���иñ�ŵ���Ʒ��Ϣ����������
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
					System.out.println("��ӳɹ�");
				} else {
					System.out.println("���ʧ��");
				}
			} else {
				// new_cart_goods�������ﳵ��Ʒ����
				//2.1.2���ﳵ��û�иñ�ŵ���Ʒ��Ϣ������µĹ��ﳵ��Ʒ��Ϣ
				Goods new_cart_goods = null;
				// ���Ҹ���Ʒ��ŵ�������Ʒ��Ϣ������������������ɹ�������
				for (int i = 0; i < MyDate.goodsArray.length; i++) {
					Goods temp_goods = MyDate.goodsArray[i];
					if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
						temp_goods.setGoods_num(1);
						new_cart_goods = temp_goods;
						break;
					}

				}
				// ���������ﳵ��Ʒ������ӵ����ﳵ��������ȥ
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
					System.out.println("��ӳɹ�");
				} else {
					System.out.println("���ʧ��");
				}
			}
			if (cart_goods != null) {
				System.out.println("��Ʒ����\t\t\t����\t����\t���");
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + cart_goods.getGoods_no() + ")" + cart_goods.getGoods_name()
						+ cart_goods.getGoods_unit() + "\t" + cart_goods.getGoods_price() + "\t"
						+ cart_goods.getGoods_num() + "\t" + (cart_goods.getGoods_price() * cart_goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
		} else {
			//2.2�����ڣ���ʾ
			System.out.println("�Բ���û�д���Ʒ��ɨ��ʧ�ܣ�����");
		}
		// 3�л����������˵�����
		cashManageGUI();
	}

	private void modifiedQuantity() {
		// 1���жϹ��ﳵ�������Ƿ��ж���
		boolean flag = false;
		for (int i = 0; i < MyDate.cartArray.length; i++) {
			if (MyDate.cartArray[i] != null) {
				flag = true;
				break;
			}
		}
		if (flag == true) {
			// 2.1������Ʒ���
			String goods_no = null;
			boolean isExist = true;
			do {
				Scanner scanner = new Scanner(System.in);
				System.out.print("����Ҫ�޸ĵ���Ʒ��ţ�");
				goods_no = scanner.next();
				// 2.2�жϹ��ﳵ���Ƿ��и���Ʒ�ı��
				boolean remark = false;
				for (int i = 0; i < MyDate.cartArray.length; i++) {
					if (MyDate.cartArray[i] != null && MyDate.cartArray[i].getGoods_no().equals(goods_no)) {
						remark = true;
						break;
					}
				}
				if (remark == false) {
					System.out.println("û��ɨ�������Ʒ");
					isExist = true;
				} else {
					isExist = false;
				}
			} while (isExist);
			//2.2.1����Ҫ�޸ĵ�����
			boolean isInteger = true;
			int goods_num = 0;
			do {
				try {
					System.out.println("�����޸�����");
					Scanner scanner = new Scanner(System.in);
					goods_num = scanner.nextInt();
					if (goods_num <= 0) {
						System.out.println("���������0�����֣�����");
						isInteger = true;
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.println("��������ȷ������������");
					isInteger = true;
				}
			} while (isInteger);
			// 2.3�������ﳵ�е��������и���Ʒ������
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
			// 2.4��ʾ�޸���Ʒ������״̬
			if (isModified) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
			if (cart_goods != null) {
				System.out.println("��Ʒ����\t\t\t����\t����\t���");
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + cart_goods.getGoods_no() + ")" + cart_goods.getGoods_name()
						+ cart_goods.getGoods_unit() + "\t" + cart_goods.getGoods_price() + "\t"
						+ cart_goods.getGoods_num() + "\t" + (cart_goods.getGoods_price() * cart_goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
			//2.5�л����������˵�
			cashManageGUI();
		} else {
			System.out.println("���ﳵ������Ʒ����ѡ��1.ɨ����Ʒ");
			cashManageGUI();
		}

	}

	private void showCartArray() {
		Goods CartArray[] = MyDate.cartArray;
		System.out.println("��ʾ���ﳵ�е�������Ϣ");
		for (Goods goods : CartArray) {
			if (goods != null) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("��Ʒ����\t\t\t����\t����\t���");
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
		// 1���жϹ��ﳵ�Ƿ�Ϊ��
		boolean isEmpty = false;
		for (int i = 0; i < MyDate.cartArray.length; i++) {
			if (MyDate.cartArray[i] != null) {
				isEmpty = true;
				break;
			}
		}

		// 2���Թ��ﳵ��ͬ״̬������Ӧ
		if (isEmpty) {
			// 2.1���ﳵ��Ϊ�գ����㹺�ﳵ���ܽ��
			double sum = 0;
			for (int i = 0; i < MyDate.cartArray.length; i++) {
				Goods temp_cart_goods = MyDate.cartArray[i];
				if (temp_cart_goods != null) {
					double amount = temp_cart_goods.getGoods_price() * temp_cart_goods.getGoods_num();
					sum += amount;
				}
			}
			System.out.println("�ܽ���ǣ�" + sum);
			System.out.println("1����ͨ����  2����Ա����");
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
					System.out.println("��ѡ��1��2");
					break;
				}
			} while (true);

		} else {
			// 2.2���ﳵΪ�գ���ʾ������������
			System.out.println("���ﳵΪ�գ���ѡ��1ɨ����Ʒ����");
			cashManageGUI();
		}

	}

	private void memberCheckOut(double sum) {
		// 1��������ȷ�Ļ�Ա����
		String member_no = "";
		Member cuurent_memberMember = null;
		boolean flag = true;
		do {
			String member_noString = null;
			Scanner scanner = new Scanner(System.in);
			System.out.print("���������Ļ�Ա���ţ�");
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
				System.out.println("��Ա���Ŵ��󣡣���");
				continue;
			}
		} while (flag);
		// 2��������ȷ�ĸ�����
		double money = 0;
		System.out.print("��������Ҫ֧���Ľ�");
		do {
			try {
				Scanner scanner = new Scanner(System.in);
				money = scanner.nextDouble();
				if (money < sum) {
					System.out.println("֧�����㣬���������룡����");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("��������ȷ��ʽ�Ľ�����");
			}
		} while (true);
		// 3����ӡ����СƱ
		System.out.println("                               ����");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String xiaopiaohaoString = simpleDateFormat.format(date);
		System.out.println("����Ա�ţ�" + this.login_user.getUser_no() + "      СƱ�ţ�" + xiaopiaohaoString);
		System.out.println("���\t��Ʒ����\t\t\t����\t����\t���\t");
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
		System.out.println("��������" + totalNum + "\t Ӧ�գ�" + sum);
		System.out.println("ʵ�գ�" + money + "\t���㣺" + (money - sum));
		System.out.println("�����ۼƻ��ֳɹ�      ���ţ�" + member_no);
		System.out.println("����ǰ����+�������ѻ���=���Ѻ����");
		int thisIntegral = (int) sum;// ���λ���
		int afterConsumptionIntegral = cuurent_memberMember.getIntegral() + (int) sum;
		System.out.println(cuurent_memberMember.getIntegral() + "+" + thisIntegral + "=" + afterConsumptionIntegral);
		// ������Ա�����еĻ���
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
		System.out.println("�����Ʊ���СƱ���Ա���ʱ֮��");
		System.out.println("=======================================================================");
		MyDate.cartArray = new Goods[50];
		cashManageGUI();

	}

	private void generalCheckOut(double sum) {
		System.out.print("��������Ҫ֧���Ľ�");
		double money = 0;
		do {
			try {
				Scanner scanner = new Scanner(System.in);
				money = scanner.nextDouble();
				if (money < sum) {
					System.out.println("֧�����㣬���������룡����");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("��������ȷ��ʽ�Ľ�����");
			}
		} while (true);

		System.out.println("                               ����");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String xiaopiaohaoString = simpleDateFormat.format(date);
		System.out.println("����Ա�ţ�" + this.login_user.getUser_no() + "      СƱ�ţ�" + xiaopiaohaoString);
		System.out.println("���\t��Ʒ����\t\t\t����\t����\t���\t");
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
		System.out.println("��������" + totalNum + "\t Ӧ�գ�" + sum);
		System.out.println("ʵ�գ�" + money + "\t���㣺" + (money - sum));

		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/    HH:mm:ss");
		String dateString = simpleDateFormat.format(date);
		System.out.println(dateString);
		System.out.println("�����Ʊ���СƱ���Ա���ʱ֮��");
		System.out.println("=======================================================================");
		MyDate.cartArray = new Goods[50];
		cashManageGUI();
	}

	/**
	 * ��ʾ�������������
	 */
	private void storeManageGUI() {
		System.out.println("******************��ӭ��¼  ������ϵͳ******************");
		System.out.println("��ѡ����еĲ�����1����Ʒ��� 2����Ʒ���� 3��������Ʒ 4����ѯȫ����Ʒ 5������ѯ�Ų�ѯ��Ʒ 6���˳�");
		boolean Lethe = true;
		do {
			Scanner scanner = new Scanner(System.in);
			String numString = scanner.next();
			switch (numString) {
			case "1":
				goodsWarehousing();// ��Ʒ���
				Lethe = false;
				break;
			case "2":
				goodsWarehousOut();// ��Ʒ����
				Lethe = false;
				break;
			case "3":
				addGoods();// ������Ʒ
				Lethe = false;
				break;
			case "4":
				queryAllGoods();// ��ѯȫ����Ʒ
				Lethe = false;
				break;
			case "5":
				queryProuductsByQueryNumber();// ����ѯ�Ų�ѯ��Ʒ
				Lethe = false;
				break;
			case "6":
				displaySystemGUI();// �˳�
				break;
			default:
				System.out.println("������1-6��ֵ");
			}
		} while (Lethe);
	}

	/**
	 * ��Ʒ���
	 */
	private void goodsWarehousing() {
		// 1��������Ʒ��ţ�����֤�Ƿ��и���Ʒ
		String goods_no = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("������Ʒ��ţ�");
		goods_no = scanner.next();

		boolean aloha = false;
		for (int i = 0; i < MyDate.goodsArray.length; i++) {
			Goods temp_goods = MyDate.goodsArray[i];
			if (temp_goods!= null && temp_goods.getGoods_no().equals(goods_no)) {
				aloha = true;
				break;
			}
		}

		// 2���ж��Ƿ��д���Ʒ
		if (aloha) {
			// 2.1.1���У���������
			int goods_num = 0;
			do {
				try {
					scanner = new Scanner(System.in);
					System.out.print("������Ʒ��������");
					goods_num = scanner.nextInt();
					if (goods_num < 0) {
						System.out.println("���������Ǵ���0������");
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.println("��������ȷ������������");
				}
			} while (true);
			// 2.1.2�Ը���Ʒ���������е���
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
			// 2.1.3���״̬�����ɹ�ORʧ��
			if (remark) {
				System.out.println("���ɹ�");
			} else {
				System.out.println("���ʧ��");
			}
		} else {
			// 2.2û�д���Ʒ����ѡ����
			System.out.println("û�д���Ʒ����ѡ���� 3:������Ʒ����");
		}
		// 3����ʾ������Ʒ��Ϣ
		// 4����ʾ���������
		queryAllGoods();
	}

	/**
	 * ��Ʒ����
	 */
	private void goodsWarehousOut() {
		// 1��������Ʒ��ţ�����֤�Ƿ��д���Ʒ
		Scanner scanner = null;
		String goods_no = "";
		do {
			scanner = new Scanner(System.in);
			System.out.print("������Ʒ��ţ�");
			goods_no = scanner.next();
			//2����֤�Ƿ��д���Ʒ
			boolean Lethe = false;
			for (int i = 0; i < MyDate.goodsArray.length; i++) {
				Goods temp_goods = MyDate.goodsArray[i];
				if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
					Lethe = true;
					break;
				}
			}
			if (Lethe == false) {
				System.out.println("û�д���Ʒ����");
				continue;
			}
			break;
		} while (true);
		int out_num = 0;
		do {
			try {
				System.out.print("����������");
				scanner = new Scanner(System.in);
				out_num = scanner.nextInt();
				if (out_num <= 0) {
					System.out.println("���������������0");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("��������ȷ������");
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
			System.out.println("����ɹ�");
			queryAllGoods();
		} else {
			System.out.println("��治�㣬���ʵ������ʧ�ܣ�����");
			System.out.println("��Ʒ���\t��Ʒ����\t\t��Ʒ����\t��λ\t����");
			System.out.println(
					old_goods.getGoods_no() + "\t\t" + old_goods.getGoods_name() + "\t\t" + old_goods.getGoods_price()
							+ "\t\t" + old_goods.getGoods_unit() + "\t\t" + old_goods.getGoods_num());
			storeManageGUI();
		}
	}
/**
 * ������Ʒ
 */
	private void addGoods() {
		Scanner scanner = null;
		// A����ȡ��ȷ��Ʒ���
		String goods_no = "";
		boolean Lethe = false;
		do {
			// 2����֤
			// 2.1��֤����Ƿ����
			scanner = new Scanner(System.in);
			System.out.print("��������Ʒ��ţ�");
			goods_no = scanner.next();
			boolean remark = true;
			Goods goodsArray[] = MyDate.goodsArray;
			for (int i = 0; i < goodsArray.length; i++) {
				Goods goods = goodsArray[i];
				if (goods != null && goods.getGoods_no().equals(goods_no)) {
					System.out.println("�˱���Ѵ��ڣ����������룡");
					remark = false;
					break;
				}
			}
			// 2.2��֤����Ƿ����
			// remark--true �ı�ò����ڣ��˳�ѭ��
			// remark--false �ñ���Ѵ��ڣ�����ѭ��
			if (remark == true) {
				Lethe = false;
			} else {
				Lethe = true;
			}
		} while (Lethe);
		// B�����λ�ȡ��Ʒ���ơ���Ʒ�۸���Ʒ��λ����Ʒ������Ӧ�б�Ҫ�жϡ�
		// 1����ȡ��Ʒ����
		scanner = new Scanner(System.in);
		System.out.print("������Ʒ����");
		String goods_name = scanner.next();
		// 2����ȡ��Ʒ�۸�
		double goods_price = 0;
		Lethe = true;
		do {
			try {
				scanner = new Scanner(System.in);
				System.out.print("������Ʒ�۸�");
				goods_price = scanner.nextDouble();
				Lethe = false;
			} catch (Exception e) {
				System.out.println("��������ȷ�ļ۸�");
			}
		} while (Lethe);
		// 3����ȡ��Ʒ��λ
		System.out.print("������Ʒ��λ");
		String goods_unit = scanner.next();
		// 4����ȡ��Ʒ����
		int goods_num = 0;
		Lethe = true;
		do {
			try {
				scanner = new Scanner(System.in);
				System.out.print("������Ʒ������");
				goods_num = scanner.nextInt();
				Lethe = false;
			} catch (Exception e) {
				System.out.println("��������ȷ������");
			}
		} while (Lethe);
		// C�����ݵı��棺������Ч����Ʒ��Ϣ���Ա���������з�����
		// 1����װ
		Goods new_goods = new Goods();// ��������
		new_goods.setGoods_no(goods_no);
		new_goods.setGoods_name(goods_name);
		new_goods.setGoods_price(goods_price);
		new_goods.setGoods_unit(goods_unit);
		new_goods.setGoods_num(goods_num);
		// 2����������Ʒ������ӵ����е���Ʒ������
		boolean result = false;
		for (int i = 0; i < MyDate.goodsArray.length; i++) {
			Goods temp_goodsGoods = MyDate.goodsArray[i];
			if (temp_goodsGoods == null) {
				MyDate.goodsArray[i] = new_goods;
				result = true;
				break;
			}
		}
		// 3�������Ƿ�������Ʒ�ɹ�״̬���б�Ҫ����ʾ
		if (result) {
			System.out.println("������Ʒ�ɹ�");
		} else {
			System.out.println("������Ʒʧ��");

		}
		// D��������ʾȫ�������Ʒ����Ʒ��š���Ʒ���ơ���Ʒ�۸���Ʒ��λ����Ʒ����

		// E�����ؿ�����˵�
		queryAllGoods();
	}
/**
 * ��ѯȫ����Ʒ
 */
	private void queryAllGoods() {
		Goods goodsArray[] = MyDate.goodsArray;
//		System.out.println("��ѯȫ����Ʒ");
		System.out.println("��Ʒ���\t��Ʒ����\t\t����\t��λ\t����");
		for (Goods goods : goodsArray) {
			if (goods != null) {

				System.out.println(goods.getGoods_no() + "\t\t" + goods.getGoods_name() + "\t\t"
						+ goods.getGoods_price() + "\t\t" + goods.getGoods_unit() + "\t\t" + goods.getGoods_num());
			}
		}
		storeManageGUI();
	}
/**
 * ����ѯ�Ų�ѯ��Ʒ
 */
	private void queryProuductsByQueryNumber() {
		// 1��������Ʒ���
		Scanner scanner = new Scanner(System.in);
		System.out.print("������Ʒ���");
		String goods_no = scanner.next();
		// 2���ȶ���Ʒ���飬������ڣ��������Ʒ��Ϣ
		Goods goodsArray[] = MyDate.goodsArray;
		boolean Lethe = true;
		for (Goods goods : goodsArray) {

			if (goods != null) {
				if (goods.getGoods_no().equals(goods_no)) {
					System.out.println("��Ʒ��� ��Ʒ���� ��Ʒ���� ��λ ����");
					System.out.println(goods.getGoods_no() + "\t" + goods.getGoods_name() + "\t"
							+ goods.getGoods_price() + "\t" + goods.getGoods_unit() + "\t" + goods.getGoods_num());
					Lethe = false;
					break;
				}
			}
		}
		if (Lethe) {
			System.out.println("�Բ���û�д���Ʒ��");
		}
		storeManageGUI();

	}
}
