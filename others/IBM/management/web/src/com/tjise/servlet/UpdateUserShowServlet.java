package com.tjise.servlet;

import com.tjise.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "UpdateUserShowServlet", urlPatterns = "/updateshow")
public class UpdateUserShowServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int uid = Integer.valueOf(request.getParameter("uid"));
        UserService userService = new UserService();
        try {
            request.getSession().setAttribute("user", userService.queryUser(uid));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("updateuser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
