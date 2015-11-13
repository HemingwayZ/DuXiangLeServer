package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.bean.UserInfo;

/**
 * �û�
 * 
 * @author zhuanghm
 *
 */
public interface UserDao {
	public List<User> findUserById(int id);

	/**
	 * �����û��������û�
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserByUserName(String userName);

	/**
	 * ���ӣ�ע�ᣩ�û�
	 * 
	 * @param user
	 * @return
	 */
	public boolean registerUser(User user);

	/**
	 * �����û����޸��û�����
	 * 
	 * @param username
	 * @return
	 */
	public boolean updatePassword(String username, String password);

	/**
	 * �����û���id��ȡ�û�����Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserName(int userId);

	/**
	 * �����û���Ϣ
	 * 
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfo(UserInfo userInfo);

	/**
	 * �����û�������Ϣ
	 * 
	 * @param userInfo
	 * @return
	 */
	public int insertUserInfo(UserInfo userInfo);

	/**
	 * �����û�������Ϣ
	 * 
	 * @param userInfo
	 * @return
	 */
	public int insertUserInfoWithPicWall(UserInfo userInfo);
	/**
	 * ��ҳ��ȡ�û���Ϣ
	 * @param begin ��ʼλ�ã�>=0��
	 * @param rowPerPage ÿҳ����
	 * @return
	 */
	public List<UserInfo> getUserInfoList(int begin,int rowPerPage);

	/**
	 * ��ȡ�û�����
	 * 
	 * @return
	 */
	public int getUserCount();
	
	public int updatePicWall(UserInfo userinfo);
	/**
	 * �����û�id��ȡ�û���Ϣ
	 * @param userInfoId
	 * @return
	 */
	public int findUserInfoByUserInfoId(String userInfoId);

	int updateUserInfoWithoutAvatar(UserInfo userInfo);
	
}
