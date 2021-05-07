package com.liuxubin.bean;

/**
 * @开发者 刘续斌
 * @类别 javaBean 对象
 * @开发日期 2020-07-22
 * @结束日期2020-07-29
 * @版本 v1.0
 * @说明 用于封装用户信息
 *
 */
public class User {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String userpwd;
	/**
	 * 用户类型 1-库存管理员 2-收银
	 */
	private String usertype;
	/**
	 * 用户编号
	 */
	private String user_no;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

}
