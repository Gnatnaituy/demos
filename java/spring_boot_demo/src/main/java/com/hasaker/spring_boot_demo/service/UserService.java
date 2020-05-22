package com.hasaker.spring_boot_demo.service;

import com.hasaker.spring_boot_demo.bean.User;

import java.util.List;

public interface UserService {
    int add(User user);
    int update(User user);
    int deleteByUsername(String username);
    List<User> queryAll();
    User queryUserByUsername(String username);
}
