package com.zhm.duxiangle.dao;

import java.util.List;

import com.zhm.duxiangle.bean.Friends;
import com.zhm.duxiangle.bean.UserInfo;

public interface FriendsDao {
	/**
	 * ¹Ø×¢ºÃÓÑ
	 * @param userid
	 * @param friendid
	 * @return
	 */
	public int addFriends(int userid,int friendid);
	
	public boolean isMyFriends(int userid,int friendid);
	
	public List<Friends> getFriendsByUserid(int userid);
	
	public boolean removeMyFriend(int userid,int friendid);
	
	public List<UserInfo> getFriendsInfo(int userid);
}
