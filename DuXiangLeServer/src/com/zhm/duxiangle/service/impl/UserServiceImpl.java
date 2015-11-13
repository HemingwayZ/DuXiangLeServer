package com.zhm.duxiangle.service.impl;

import java.util.List;

import com.zhm.duxiangle.bean.Page;
import com.zhm.duxiangle.bean.UserInfo;
import com.zhm.duxiangle.dao.UserDao;
import com.zhm.duxiangle.dao.impl.UserDaoImpl;
import com.zhm.duxiangle.service.UserService;
import com.zhm.duxiangle.utils.TextUtils;

public class UserServiceImpl implements UserService {

	private UserDao dao = new UserDaoImpl();

	@Override
	public UserInfo getUserInfoByUserId(String userId) {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(userId)) {
			return null;
		}
		return dao.getUserInfoByUserName(Integer.valueOf(userId));
	}

	@Override
	public Page getUserInfoListByPage(int thispage, int rowperpage) {
		Page page = new Page();
		// 1����ǰҳ��ҳ��
		page.setThispage(thispage);
		// 2��ÿҳ��¼��
		page.setRowperpage(rowperpage);
		// 3���ܼ�¼��
		int countrow = dao.getUserCount();
		page.setCountrow(countrow);
		// 4����ҳ��
		int countpage = countrow / (rowperpage == 0 ? 1 : rowperpage) + ((countrow % rowperpage) == 0 ? 0 : 1);
		page.setCountpage(countpage);
		// 5����ҳ
		page.setFirstpage(1);
		// 6��βҳ
		page.setLastpage(countpage);
		// 7����һҳ
		page.setPrepage(thispage == 0 ? 0 : thispage - 1);
		// 8����һҳ
		page.setNextpage(thispage == countpage ? countpage : thispage + 1);
		// 9��ÿҳ����--�ͻ�
		List<UserInfo> infoList = dao.getUserInfoList(thispage, rowperpage);
		page.setList(infoList);
		return page;
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		UserInfo info = dao.getUserInfoByUserName(Integer.valueOf(userInfo.getUserId()));
		if (info == null) {
			return dao.insertUserInfo(userInfo);
		} else {
			return dao.updateUserInfo(userInfo);
		}
	}

	@Override
	public int updateUserInfoWithoutAvatar(UserInfo userInfo) {
		UserInfo info = dao.getUserInfoByUserName(Integer.valueOf(userInfo.getUserId()));
		if (info == null) {
			return dao.insertUserInfo(userInfo);
		} else {
			return dao.updateUserInfoWithoutAvatar(userInfo);
		}
	}

	@Override
	public int updatePicWall(UserInfo userInfo) {
		// TODO Auto-generated method stub
		UserInfo info = dao.getUserInfoByUserName(Integer.valueOf(userInfo.getUserId()));
		if (info == null) {
			return dao.insertUserInfoWithPicWall(userInfo);
		} else {
			return dao.updatePicWall(userInfo);
		}
	}

}
