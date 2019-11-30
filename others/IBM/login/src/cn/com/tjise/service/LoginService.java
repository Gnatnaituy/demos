package cn.com.tjise.service;

import cn.com.tjise.pojo.User;

//import cn.com.tjise.pojo.User;


/*
 * 创建一个业务接口
 * */
public interface LoginService {
	User checkLoginService(String uname, String pwd);

	User checkUidService(String uid);
}
