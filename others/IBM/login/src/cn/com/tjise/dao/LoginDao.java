package cn.com.tjise.dao;

import cn.com.tjise.pojo.User;

//创建dao接口
public interface LoginDao {
	User checkLoginDao(String uname,String pwd);
}
