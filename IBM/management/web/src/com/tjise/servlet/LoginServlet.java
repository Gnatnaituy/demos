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


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String uname = request.getParameter("uname");
        String pwd= request.getParameter("pwd");
        UserService userService = new UserService();
        User user = null;
        String info;

        try {
            user = userService.queryUser(uname);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user != null) {
            if (user.getPwd().equals(pwd)) {
                request.getSession().setAttribute("logineduser", user.getUid());
                info = "登录成功!";
            } else {
                info = "密码错误!";
            }
        } else {
            info = "用户不存在!";
        }

        request.getSession().setAttribute("info", info);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }
}
