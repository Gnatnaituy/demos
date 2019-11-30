package com.tjise.service;

import com.tjise.dao.UserDao;
import com.tjise.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDao userDao = new UserDao();

    public boolean addUser(User user) throws SQLException {
        return userDao.addUser(user);
    }

    public boolean updateUser(User user) throws SQLException {
        return userDao.updateUser(user);
    }

    public User queryUser(int uid) throws SQLException {
        return userDao.queryUser(uid);
    }

    public User queryUser(String uname) throws SQLException {
        return userDao.queryUser(uname);
    }

    public List<User> queryAllUser() throws SQLException {
        return userDao.queryAllUser();
    }

    public boolean deleteUser(int uid) throws SQLException {
        return userDao.deleteUser(uid);
    }
}
