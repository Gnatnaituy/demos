package jdbc;

import java.sql.*;

public class DBUtilities {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String DB_PARAMS = "?serverTimezone=UTC&useCursorFetch=true";
    private static final String USERNAME = "Hasaker";
    private static final String PASSWORD = "5523";

    private static Connection connection = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + DB_PARAMS, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection() {
        return connection;
    }

    public static void closeConnections(ResultSet r, Statement s, Connection c) {
        try {
            if (r != null) {
                r.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
