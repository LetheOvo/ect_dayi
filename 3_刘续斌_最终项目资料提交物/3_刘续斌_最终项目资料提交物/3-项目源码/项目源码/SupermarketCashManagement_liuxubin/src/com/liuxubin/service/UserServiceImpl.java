package com.liuxubin.service;

import com.liuxubin.bean.User;
import com.liuxubin.util.MyDate;
/**
 * @������ ������
 * @��� ҵ����Ľӿ�
 * @�������� 2020-07-21
 * @�������� 2020-07-29
 * @�汾 v1.0
 * @˵�� ���ڷ�װҵ���ܵ�ʵ��
 * @author 80580
 *
 */
public class UserServiceImpl implements UserService {
	MyDate mydate = new MyDate();

	@Override
	public User loginByUsernameAndUserpwd(String username, String userpwd) {
		User user = null;
		User userArray[] = MyDate.userArray;
		for (int i = 0; i < userArray.length; i++) {
			User temp_user = userArray[i];
			if (temp_user != null) {
				if (temp_user.getUsername().equals(username) && temp_user.getUserpwd().equals(userpwd)) {
//					user = new User();
//					user.setUsername(username);
//					// user.setUserpwd(userpwd);
//					user.setUsertype(temp_user.getUsertype());
					user = temp_user;
					break;
				}
			}
		}
		return user;
	}
}
