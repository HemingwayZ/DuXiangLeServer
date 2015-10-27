package com.zhm.duxiangle.utils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * @author 作者：zhm
 * @version 创建时间：2015年6月9日 上午10:40:13
 */
public class DaoUtils {
	private static DaoUtils daoUtils = new DaoUtils();

	private DaoUtils() {
	}

	private static InputStream in = null;
	private static ComboPooledDataSource dataSource = null;
	private static Properties properties = null;
	private static String driver = null;
	private static String jdbcUrl = null;
	private static String user = null;
	private static String password = null;

	static {
		in = DBUtil.class.getClassLoader().getResourceAsStream("Jdbc.properties");
		properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		driver = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");

		dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		try {
			dataSource.setDriverClass(driver);
		} catch (PropertyVetoException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public static DataSource getSource() {
		return dataSource;
	}

	public static Connection getCoon() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取资源连接异常" + e);
		}
	}
}
