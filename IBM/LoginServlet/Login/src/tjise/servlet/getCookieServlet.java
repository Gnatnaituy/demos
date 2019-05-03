package tjise.servlet;

import tjise.pojo.User;
import tjise.service.LoginService;
import tjise.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class getCookieServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //1.获取请求信息req
        Cookie[] cks = req.getCookies();
        String uid = "";

        //2.处理请求信息----MVC
        if (cks != null) {
            for (Cookie c : cks) {//cks里面存放大量的cookie，我们只找寻uid对应的cookie值
                if ("uid".equals(c.getName())) {
                    uid = c.getValue();
                }
            }

            if ("".equals(uid)) {
                System.out.println("cookie中的uid为空:" + uid);
            } else {
                //3.响应处理信息
                //校验uid用户信息---->业务对象
                LoginService ls = new LoginServiceImpl();//jdbc
                User u = ls.checkUidService(uid);//重新创建一个新的接口方法以及对应的实现类方法   LS-->LSI-->LD-->LDI-->JDBC
                if (u != null) {
                    System.out.println("有cookie的uid: fuck" + uid);
                    //获取用户数据
                    req.getSession().setAttribute("user", u);
                    //重定向
                    resp.sendRedirect("/Login/main");
                } else {
                    System.out.println("..没有cookie的uid：" + uid);
                    //请求转发
                    req.getRequestDispatcher("page").forward(req, resp);
                }
            }
        } else {
            System.out.println("没有cookie的uid：" + uid);
            //请求转发
            req.getRequestDispatcher("page").forward(req, resp);
        }
    }
}





