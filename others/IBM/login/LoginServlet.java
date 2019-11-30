package cn.com.tjise.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import cn.com.tjise.pojo.User;
import cn.com.tjise.service.LoginService;
import cn.com.tjise.service.impl.LoginServiceImpl;

/*
 * 创建一个处理登录请求servlet
 *1.获取请求数据
 *2.处理数据——业务分析
 *3.展示相应的结果——分发转向
 * */
public class LoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String uname = req.getParameter("uname"); // parameter--参数
		String pwd = req.getParameter("pwd");
		System.out.println("用户名" + uname);
		System.out.println("密码" + pwd);
		LoginService ls = new LoginServiceImpl();
		User u = ls.checkLoginService(uname, pwd);
		if (u != null) {
			resp.getWriter().write("登录成功！");
			// 创建cookie7天免登录
			Cookie c = new Cookie("uid", u.getUid() + "");
			c.setMaxAge(7 * 24 * 3600);
			resp.addCookie(c);
			HttpSession hs = req.getSession();
			// 重定向到getC页面
			hs.setAttribute("user", u);
			resp.sendRedirect("getC");
		} else {
			resp.getWriter().write("登录失败！");
			// 转发到page页面
//			req.getRequestDispatcher("/page").forward(req, resp);

		}

	}
}
