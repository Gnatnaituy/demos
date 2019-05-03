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

@WebServlet(name = "USerInfoServlet", urlPatterns = "/userinfo")
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        UserService userService = new UserService();
        User user = null;

        try {
            user = userService.queryUser(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher("userinfo.jsp").forward(request, response);
    }
}
