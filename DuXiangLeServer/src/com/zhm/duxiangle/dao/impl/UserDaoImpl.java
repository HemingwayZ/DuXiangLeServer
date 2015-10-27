package com.zhm.duxiangle.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.utils.DaoUtils;

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
		if (runner == null)
			runner = new QueryRunner(DaoUtils.getSource());
		sql = "select * from user where userName = ?";
		try {
			return runner.query(sql, new BeanHandler<>(User.class), userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
