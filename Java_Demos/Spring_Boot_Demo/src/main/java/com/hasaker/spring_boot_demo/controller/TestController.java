package com.hasaker.spring_boot_demo.controller;


import com.hasaker.spring_boot_demo.bean.User;
import com.hasaker.spring_boot_demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    public User queryUserByUsername(String username) {

        return this.userService.queryUserByUsername(username);
    }

    @RequestMapping(value = "/queryAllUser", method = RequestMethod.GET)
    public List<Map<String, Object>> queryAllUser() {

        return this.userService.queryUserListMap();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public int saveUser(String username, int age, String password) {
        User user = new User();
        user.setUsername(username);
        user.setAge(age);
        user.setPassword(password);

        return this.userService.add(user);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public int deleteUserByUsername(String username) {

        return this.userService.deleteByUsername(username);
    }
}
