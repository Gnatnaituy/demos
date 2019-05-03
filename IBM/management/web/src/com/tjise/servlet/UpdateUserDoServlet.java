package com.tjise.servlet;


import com.tjise.model.User;
import com.tjise.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "UpdateUserDoServlet", urlPatterns = "/updatedo")
public class UpdateUserDoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        UserService userService = new UserService();
        User user;

        try {
            user = userService.queryUser(Integer.valueOf(request.getParameter("uid")));
            user.setUname(request.getParameter("uname"));
            user.setPwd(request.getParameter("pwd"));
            user.setSex(request.getParameter("sex"));
            user.setAge(Integer.parseInt(request.getParameter("age")));
            user.setBirth(LocalDate.parse(request.getParameter("birth")));
            userService.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("queryall");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
