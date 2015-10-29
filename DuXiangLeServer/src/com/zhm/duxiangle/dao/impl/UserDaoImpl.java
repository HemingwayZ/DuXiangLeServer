package com.zhm.duxiangle.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.utils.DaoUtils;
import com.zhm.duxiangle.utils.MD5Util;

public class UserDaoImpl implements UserDao {
	String sql = "";
	QueryRunner runner = new QueryRunner(DaoUtils.getSource());

	public List<User> findUserById(int id) {
		if (runner == null)
			runner = new QueryRunner(DaoUtils.getSource());
		// !!!bean的属性要和数据库的属性同名
		sql = "select * from user where userId = ?";
		try {
			return runner.query(sql, new BeanListHandler<>(User.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public User findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		if (runner == null) {
			runner = new QueryRunner(DaoUtils.getSource());
		}
		sql = "select * from user where userName = ?";
		try {
			return runner.query(sql, new BeanHandler<>(User.class), userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(MD5Util.endodeStr2MD5(user.getPassword()));
		sql = "insert into user values(null,?,?,null)";
		if (runner == null) {
			runner = new QueryRunner(DaoUtils.getSource());
		}
		try {
			int i = runner.update(sql, user.getUserName(), user.getPassword());
			if (i == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		password = MD5Util.endodeStr2MD5(password);
		sql = "update user set password = ? where username = ?";
		if (runner == null) {
			runner = new QueryRunner(DaoUtils.getSource());
		}
		try {
			int i = runner.update(sql, password, username);
			if (i == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
