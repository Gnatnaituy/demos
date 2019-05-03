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
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "QueryAllUSerServlet", urlPatterns = "/queryall")
public class QueryAllUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Login in needed
        if (request.getSession().getAttribute("logineduser") == null) {
            request.getSession().setAttribute("info", "请先登录!");
            request.getRequestDispatcher("info.jsp").forward(request, response);
        } else {
            List<User> users = new ArrayList<>();
            UserService userService = new UserService();

            try {
                users = userService.queryAllUser();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.getSession().setAttribute("users", users);
            request.getRequestDispatcher("allusers.jsp").forward(request, response);
        }
    }
}
