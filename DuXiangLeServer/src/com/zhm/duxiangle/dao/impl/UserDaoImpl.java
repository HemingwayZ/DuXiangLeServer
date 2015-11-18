package com.zhm.duxiangle.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zhm.duxiangle.bean.User;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.utils.DaoUtils;
import com.zhm.duxiangle.utils.MD5Util;
import com.zhm.duxiangle.utils.TextUtils;

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
	public User getUserByUserName(String userName) {
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
		if (!TextUtils.isEmpty(user.getPassword()))
			user.setPassword(MD5Util.endodeStr2MD5(user.getPassword()));
		sql = "insert into user values(null,?,?,?,null,null,null)";
		if (runner == null) {
			runner = new QueryRunner(DaoUtils.getSource());
		}
		try {
			int i = runner.update(sql, user.getUserName(), user.getPassword(), "offline");
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

	@Override
	public UserInfo getUserInfoByUserid(int userId) {
		sql = "select * from userinfo where userId = ?";
		try {
			return runner.query(sql, new BeanHandler<>(UserInfo.class), userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		sql = "update userinfo set nickname=?,avatar=?,describ=?,created=? where userid=?";
		System.out.println(userInfo.getUserinfoId());
		try {
			return runner.update(sql, userInfo.getNickname(), userInfo.getAvatar(), userInfo.getDescrib(),
					userInfo.getCreated(), userInfo.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		sql = "insert into userinfo values(null,?,?,?,?,?,?)";

		try {
			return runner.update(sql, userInfo.getUserId(), userInfo.getNickname(), userInfo.getPicWall(),
					userInfo.getAvatar(), userInfo.getCreated(), userInfo.getDescrib());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUserInfoWithoutAvatar(UserInfo userInfo) {
		// TODO Auto-generated method stub
		sql = "update userinfo set nickname=?,describ=?,created=? where userid=?";
		System.out.println(userInfo.getUserinfoId());
		try {
			return runner.update(sql, userInfo.getNickname(), userInfo.getDescrib(), userInfo.getCreated(),
					userInfo.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<UserInfo> getUserInfoList(int thispage, int rowPerPage) {
		// TODO Auto-generated method stub
		sql = "select * from userinfo limit ?,?";
		try {
			return runner.query(sql, new BeanListHandler<UserInfo>(UserInfo.class), thispage, rowPerPage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getUserCount() {
		sql = "SELECT count(userinfoId) FROM duxiangle_db.userinfo";
		try {
			return ((Long) runner.query(sql, new ScalarHandler())).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updatePicWall(UserInfo userInfo) {
		// TODO Auto-generated method stub
		sql = "update userinfo set picWall = ? where userid=?";
		System.out.println(userInfo.getUserinfoId());
		try {
			return runner.update(sql, userInfo.getPicWall(), userInfo.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int findUserInfoByUserInfoId(String userInfoId) {
		// TODO Auto-generated method stub
		sql = "select * from userinfo where userinfoid=?";

		try {
			runner.query(sql, new BeanHandler<UserInfo>(UserInfo.class), userInfoId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertUserInfoWithPicWall(UserInfo userInfo) {
		// TODO Auto-generated method stub
		sql = "insert into userinfo values(null,?,null,?,null,null,null)";

		try {
			return runner.update(sql, userInfo.getUserId(), userInfo.getPicWall());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<UserInfo> findUserInfoByKeyWords(String keywords) {
		keywords = "%" + keywords + "%";
		sql = "select * from userinfo where nickname like ?";
		try {
			return runner.query(sql, new BeanListHandler<UserInfo>(UserInfo.class), keywords);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean registerByAuth(User user) {
		sql = "insert into user values(null,null,null,null,?,?,?)";
		if (runner == null) {
			runner = new QueryRunner(DaoUtils.getSource());
		}
		try {
			int i = runner.update(sql, user.getOpenid(), user.getAccess_token(), user.getAuth_type());
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
	public User getUserByOpenid(String openid) {
		// TODO Auto-generated method stub
		if (runner == null) {
			runner = new QueryRunner(DaoUtils.getSource());
		}
		sql = "select * from user where openid = ?";
		try {
			return runner.query(sql, new BeanHandler<>(User.class), openid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateUserInfoAuthByQQ(UserInfo userInfo) {
		// TODO Auto-generated method stub
				sql = "update userinfo set nickname=?,avatar=? where userid=?";
				System.out.println(userInfo.getUserinfoId());
				try {
					return runner.update(sql, userInfo.getNickname(), userInfo.getAvatar(), userInfo.getUserId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
	}
}
