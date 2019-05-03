package cn.com.tjise.service.impl;

import cn.com.tjise.service.LoginService;
import cn.com.tjise.dao.LoginDao;
import cn.com.tjise.dao.impl.LoginDaoImpl;
import cn.com.tjise.pojo.User;
import cn.com.tjise.service.LoginService;

public class LoginServiceImpl implements LoginService {

	@Override
	public User checkLoginService(String uname, String pwd) {
		LoginDao id = new LoginDaoImpl();
		User u = id.checkLoginDao(uname, pwd);
		return u;
	}

	@Override
	public User checkUidService(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

}
