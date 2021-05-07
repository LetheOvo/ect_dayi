package com.liuxubin.service;

import com.liuxubin.bean.User;
/**
 * @开发者 刘续斌
 * @类别 业务类的接口
 * @开始日期 2020-07-21
 * @结束日期 2020-07-29
 * @版本 v1.0
 * @说明 用于封装用户业务功能的接口
 * @author 80580
 *
 */

public interface UserService {
	/**
	 * 用于处理 登陆操作的业务处理方法
	 * 
	 * @param username 用户名
	 * @param userpwd 密码
	 * @return 用户对象
	 *   成功：user不为null
	 *   失败：user为null
	 */
	public User loginByUsernameAndUserpwd(String username, String userpwd);
}
