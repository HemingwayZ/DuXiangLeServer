package com.zhm.duxiangle.service;

import java.util.List;

import com.zhm.duxiangle.bean.Page;
import com.zhm.duxiangle.bean.UserInfo;

/**
 * ����㣬�û��߼�
 * 
 * @author zhuanghm
 *
 */
public interface UserService {
	/**
	 * �����û�id��ȡ�û���Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserId(String userId);
	/**
	 * �߼���-��ҳ��ȡ�û���Ϣ�߼�����
	 * @param begin
	 * @param rowperpage
	 * @return
	 */
	public Page  getUserInfoListByPage(int begin, int rowperpage);
}
