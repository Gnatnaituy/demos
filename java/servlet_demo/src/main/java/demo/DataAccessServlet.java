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
        PrintWriter out = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String title = "数据库结果";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        try {
            connection = factory.makeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            out = response.getWriter();
            
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body>\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("email");

                out.print("ID: " + id + " ");
                out.print("NAME: " + username + " ");
                out.print("AGE: " + age + " ");
                out.print("EMAIL: " + password + " ");
                out.println("<br>");
            }

            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (out != null) out.close();
        }
    }
}
