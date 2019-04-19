package com.hasaker.spring_boot_demo.controller;

import com.hasaker.spring_boot_demo.bean.User;
import com.hasaker.spring_boot_demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userTable", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView mav = new ModelAndView("all_users");
        List<User> usersList = new ArrayList<>();
        List<Map<String, Object>> allUsers = userService.queryUserListMap();

        for (Map<String, Object> user : allUsers) {
            usersList.add(new User((int) user.get("id"), (String) user.get("username"),
                    (int) user.get("age"), (String) user.get("password")));
        }

        mav.addObject("userList", usersList);

        return mav;
    }
}
