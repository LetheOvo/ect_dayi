package com.liuxubin.bean;

/**
 * @������ ������
 * @��� javaBean ����
 * @�������� 2020-07-22
 * @��������2020-07-29
 * @�汾 v1.0
 * @˵�� ���ڷ�װ�û���Ϣ
 *
 */
public class User {
	/**
	 * �û���
	 */
	private String username;
	/**
	 * �û�����
	 */
	private String userpwd;
	/**
	 * �û����� 1-������Ա 2-����
	 */
	private String usertype;
	/**
	 * �û����
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
