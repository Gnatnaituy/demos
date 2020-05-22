package com.hasaker.spring_boot_demo.controller;


import com.hasaker.spring_boot_demo.bean.User;
import com.hasaker.spring_boot_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    private final UserService userService;

    @Autowired
    public TestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/query_user/{username}", method = RequestMethod.GET)
    @ResponseBody
    public User queryUserByUsername(@PathVariable String username) {

        return this.userService.queryUserByUsername(username);
    }

    @RequestMapping(value = "/query_all_users", method = RequestMethod.GET)
    public String allUsers(Model model) {
        List<User> users = this.userService.queryAll();
        model.addAttribute("users", users);

        return "all_users";
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.GET)
    public int saveUser(String username, int age, String password) {
        User user = new User();
        user.setUsername(username);
        user.setAge(age);
        user.setPassword(password);

        return this.userService.add(user);
    }

    @RequestMapping(value = "/delete_user", method = RequestMethod.GET)
    public int deleteUserByUsername(String username) {

        return this.userService.deleteByUsername(username);
    }
}
