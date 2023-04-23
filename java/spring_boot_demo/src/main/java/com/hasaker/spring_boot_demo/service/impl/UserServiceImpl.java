package com.hasaker.spring_boot_demo.service.impl;

import com.hasaker.spring_boot_demo.bean.User;
import com.hasaker.spring_boot_demo.dao.UserDao;
import com.hasaker.spring_boot_demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int add(User user) {
        return this.userDao.add(user);
    }

    @Override
    public int update(User user) {
        return this.userDao.update(user);
    }

    @Override
    public int deleteByUsername(String username) {
        return this.userDao.deleteByUsername(username);
    }

    @Override
    public List<User> queryAll() {
        return this.userDao.queryAll();
    }

    @Override
    public User queryUserByUsername(String username) {
        return this.userDao.queryUserByUsername(username);
    }
}
