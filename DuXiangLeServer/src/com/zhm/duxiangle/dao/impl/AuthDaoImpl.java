package com.zhm.duxiangle.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zhm.duxiangle.bean.Auth;
import com.zhm.duxiangle.dao.AuthDao;
import com.zhm.duxiangle.utils.DaoUtils;
import com.zhm.duxiangle.utils.TextUtils;

public class AuthDaoImpl implements AuthDao{
	String sql = "";
	QueryRunner runner = new QueryRunner(DaoUtils.getSource());
	@Override
	public Auth getAuthByOpenid(String openid) {
		if(TextUtils.isEmpty(openid)){
			return null;
		}
		sql = "select * from auth where openid = ?";
		try {
			return runner.query(sql, new BeanHandler<Auth>(Auth.class),openid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int insertAuth(Auth auth) {
		if(auth == null){
			return 0;
		}
		Auth auth2 = getAuthByOpenid(auth.getOpenid());
		if(auth!=null){//说明表格已经存在该认证信息
			return 0;
		}
		sql = "insert into auth values(null,null,?,?,?)";
		try {
			return runner.update(sql, auth.getOpenid(),auth.getAccess_token(),auth.getType());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
