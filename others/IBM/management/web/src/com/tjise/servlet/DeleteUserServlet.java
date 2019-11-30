package com.tjise.servlet;

import com.tjise.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "DeleteUserServlet", urlPatterns = "/delete")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        UserService userService = new UserService();

        try {
            userService.deleteUser(Integer.valueOf(request.getParameter("uid")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("queryall");
    }
}
