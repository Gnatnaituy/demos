package tjise.servlet;

import tjise.pojo.User;
import tjise.service.LoginService;
import tjise.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 创建一个处理登录请求的servlet
 * 1.获取请求数据
 * 2.处理数据----业务分析
 * 3.展示相应的结果----分发转向
 * 请求转发：转发 实现多个servlet联动操作处理req，避免代码冗余  分工明确
 * req.getRequestDispatcher("要转发的地址").forward(req, resp);
 * 特点： 一次性请求     浏览器地址栏信息不变
 * 重定向：解决表单重复提交的问题，当前的servlet无法再处理请求
 * 特点：两次或两次以上的请求   两个或两个以上 req对象
 * 地址栏要发生变化
 */
public class loginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //1.获取请求数据
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println("用户名：" + uname + ",密码：" + pwd);
//		resp.getWriter().write("用户名："+uname+",密码："+pwd);

        //2.处理数据----业务逻辑分析
        LoginService ls = new LoginServiceImpl();
        User u = ls.checkLoginService(uname, pwd);
        System.out.println(u);

        //3.相应处理结果                                                                                               k        v
        if (u != null) {    //当前这个u已经有数据了 在 User实体类中     uid----->uname&pwd
            //直接响应
//			resp.getWriter().write("登录成功！");
            //正确，进入首页
            //请求转发
//			req.getRequestDispatcher("main").forward(req, resp);
            //重定向
            //resp.sendRedirect("/Login/main");
            //创建cookie对象，实现7天免登陆
            Cookie c = new Cookie("uid", u.getUid() + "");
            //设置一个有效期
            c.setMaxAge(7 * 24 * 3600);
            resp.addCookie(c);
            //创建session对象
            HttpSession hs = req.getSession();//获取/创建sessionid
            hs.setAttribute("user", u);
            //重定向
            resp.sendRedirect("getc");
        } else {
            System.out.println("没有cookie");
//            resp.getWriter().write("登录失败！");
            //错误，再回到登录页面
            req.getRequestDispatcher("/page").forward(req, resp);
        }
    }
}
