package cn.com.tjise.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class oneself extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("uft-8");
		resp.setContentType("text/http,charset=utf-8");
		resp.getWriter().write("<http>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("<title>个人详情页面");
		resp.getWriter().write("</title>");
		resp.getWriter().write("</head>");
		resp.getWriter().write("<body>");
		resp.getWriter().write("<form action='' mothed=get>");
		resp.getWriter().write("input name=姓名 type=text");
		resp.getWriter().write("input name=性别  type=text");
		resp.getWriter().write("input name=政治面貌 type=text");
		resp.getWriter().write("input name=民族 type=text");
		resp.getWriter().write("<input name=爱好 type=text>");
		resp.getWriter().write("<input name=出生日 type=text>");
		resp.getWriter().write("<p> 请详细填写个人信息！");
		resp.getWriter().write("</p>");
		resp.getWriter().write("</form>");
		resp.getWriter().write("</body>");
		resp.getWriter().write("</http>");
	}
}
