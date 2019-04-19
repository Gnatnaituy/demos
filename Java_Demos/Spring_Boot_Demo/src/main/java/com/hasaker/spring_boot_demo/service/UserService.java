package com.hasaker.spring_boot_demo.service;

import com.hasaker.spring_boot_demo.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int add(User user);
    int update(User user);
    int deleteByUsername(String username);
    List<Map<String, Object>> queryUserListMap();
    User queryUserByUsername(String username);
}
