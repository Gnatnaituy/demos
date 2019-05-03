package cn.com.tjise.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mainServlet extends HttpServlet {
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	resp.setCharacterEncoding("UTF-8");
	req.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html;charset=utf-8");
	resp.getWriter().write("<html>");
	resp.getWriter().write("<head>");
	resp.getWriter().write("</head>");
	resp.getWriter().write("<body>");
	resp.getWriter().write("<p>主页面");
	resp.getWriter().write("</p>");
	resp.getWriter().write("<p>欢迎来到主页面");
	resp.getWriter().write("</p>");
	resp.getWriter().write("</body>");
	resp.getWriter().write("</html>");
}
}
