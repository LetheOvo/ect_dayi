package com.liuxubin.service;

import com.liuxubin.bean.User;
/**
 * @������ ������
 * @��� ҵ����Ľӿ�
 * @��ʼ���� 2020-07-21
 * @�������� 2020-07-29
 * @�汾 v1.0
 * @˵�� ���ڷ�װ�û�ҵ���ܵĽӿ�
 * @author 80580
 *
 */

public interface UserService {
	/**
	 * ���ڴ��� ��½������ҵ������
	 * 
	 * @param username �û���
	 * @param userpwd ����
	 * @return �û�����
	 *   �ɹ���user��Ϊnull
	 *   ʧ�ܣ�userΪnull
	 */
	public User loginByUsernameAndUserpwd(String username, String userpwd);
}
