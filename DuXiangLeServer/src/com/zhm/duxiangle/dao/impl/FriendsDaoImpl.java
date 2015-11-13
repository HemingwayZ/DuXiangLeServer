package com.zhm.duxiangle.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zhm.duxiangle.bean.Friends;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.FriendsDao;
import com.zhm.duxiangle.utils.DaoUtils;

public class FriendsDaoImpl implements FriendsDao {
	String sql = "";
	QueryRunner runner = new QueryRunner(DaoUtils.getSource());

	@Override
	public int addFriends(int userid, int friendid) {
		if (isMyFriends(userid, friendid)) {
			return 0;
		}
		sql = "insert into friends values(null,?,?)";
		try {
			return runner.update(sql, userid, friendid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ∑¿÷π÷ÿ∏¥ÃÌº”
		return 0;
	}

	@Override
	public boolean isMyFriends(int userid, int friendid) {
		sql = "select * from friends where userid= ? and friendid = ?";

		try {
			List<Friends> list = runner.query(sql, new BeanListHandler<Friends>(Friends.class), userid, friendid);
			if (list.size() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Friends> getFriendsByUserid(int userid) {
		sql = "select * from friends where userid = ?";
		try {
			return runner.query(sql, new BeanListHandler<Friends>(Friends.class), userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeMyFriend(int userid, int friendid) {
		if (!isMyFriends(userid, friendid)) {
			return false;
		}
		sql = "delete from friends where userid = ? and friendid = ?";

		try {
			int i = runner.update(sql, userid, friendid);
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<UserInfo> getFriendsInfo(int userid) {
		List<Friends> list = getFriendsByUserid(userid);
		if (list == null || list.size() <= 0) {
			return null;
		}
		List<String> list2 = new ArrayList<>();
		sql = "select * from userinfo where userid = ?";
		list2.add(String.valueOf(list.get(0).getFriendid()));
		for (int i = 1; i < list.size(); i++) {
			list2.add(String.valueOf(list.get(i).getFriendid()));
			sql += " or userid = ?";
		}
		System.out.println("getFriendsInfo:" + sql);

		try {
			return runner.query(sql, list2.toArray(), new BeanListHandler<UserInfo>(UserInfo.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
