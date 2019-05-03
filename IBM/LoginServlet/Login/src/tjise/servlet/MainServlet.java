package tjise.servlet;

import tjise.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取数据
        HttpSession hs = req.getSession();
        //获取session中保存的user对象
        User u = (User) hs.getAttribute("user");
        //响应处理结果
        // 设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("<html>");
        resp.getWriter().write("<head>");
        resp.getWriter().write("</head>");
        resp.getWriter().write("<body>");
        resp.getWriter().write("<p>主页面</p>");
        resp.getWriter().write("<p>欢迎来" + u.getUname() + "的天软官网主页</p>");
        resp.getWriter().write("<form action='show' method='get'>");
        resp.getWriter().write("<input type='submit' value='查看个人信息'>");
        resp.getWriter().write("</form>");
        resp.getWriter().write("</body>");
        resp.getWriter().write("</html>");
    }
}
