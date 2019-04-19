package demo;

import demo.utility.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DataAccessServlet extends HttpServlet {
    private ConnectionFactory factory = ConnectionFactory.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String sql = "select * from imooc_goddess";

        String title = "数据库结果";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        try (PrintWriter out = response.getWriter();
             Connection connection = factory.makeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");

                out.println("ID: " + id + "<br>");
                out.println("USERNAME: " + username + "<br>");
                out.println("AGE: " + age + "<br>");
                out.println("PASSWORD: " + password + "<br>");
            }

            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
