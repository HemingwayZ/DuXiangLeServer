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
	
	/**
	 * �����û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfo(UserInfo userInfo);
	
	/**
	 * ������Ƭǽ
	 * @param userInfo
	 * @return
	 */
	public int updatePicWall(UserInfo userInfo);
	/**
	 * �������û�������Ϣ
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfoWithoutAvatar(UserInfo userInfo);
	/**
	 * �û�����
	 * @param keywords
	 * @return
	 */
	public List<UserInfo> findUserInfoByKeyWords(String keywords);
	/**
	 * �����鼮��isbn����ҳ��ȡ�ղ��˸�����û���Ϣ
	 * @param isbn
	 */
	public Page pageUserInfoByIsbn(String isbn,int thispage,int rowperpage);
}
