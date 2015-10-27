package com.zhm.duxiangle.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	private static String driver = null;
	private static String jdbcUrl = null;
	private static String user = null;
	private static String password = null;
	private static ComboPooledDataSource dataSource = null;

	static {
		InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("Jdbc.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
			driver = properties.getProperty("driver");
			jdbcUrl = properties.getProperty("jdbcUrl");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			dataSource = new ComboPooledDataSource();

			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl(jdbcUrl);
			dataSource.setUser(user);
			dataSource.setPassword(password);
			dataSource.setMaxPoolSize(1000);
			// dataSource.setInitialPoolSize(100);
			dataSource.setMaxStatements(800);
			dataSource.setMaxStatementsPerConnection(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ȡ���ӳ�
	public static DataSource getSource() {
		return dataSource;
	}

	// ��ȡ����
	public static Connection getConnection() {

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// �ر���(��C3p0�б�ʾ�����������ӳأ�
	public static void close(Connection conn, PreparedStatement stm, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}

			if (stm != null) {
				stm.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
