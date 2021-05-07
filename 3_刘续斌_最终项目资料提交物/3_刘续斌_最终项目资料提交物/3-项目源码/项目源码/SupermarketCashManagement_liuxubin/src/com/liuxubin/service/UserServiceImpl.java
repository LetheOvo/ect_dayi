package com.liuxubin.service;

import com.liuxubin.bean.User;
import com.liuxubin.util.MyDate;
/**
 * @开发者 刘续斌
 * @类别 业务类的接口
 * @开发日期 2020-07-21
 * @结束日期 2020-07-29
 * @版本 v1.0
 * @说明 用于封装业务功能的实现
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
