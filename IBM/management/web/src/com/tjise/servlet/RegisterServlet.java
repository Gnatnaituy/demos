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


@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        User user = new User();
        UserService userService = new UserService();

        user.setUname(request.getParameter("uname"));
        user.setPwd(request.getParameter("pwd"));
        user.setSex(request.getParameter("sex"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        user.setBirth(LocalDate.parse(request.getParameter("birth")));

        try {
            userService.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("info", "注册成功!");
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
