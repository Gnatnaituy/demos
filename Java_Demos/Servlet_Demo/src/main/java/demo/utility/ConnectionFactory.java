package demo.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/demo?serverTimezone=UTC";
    private static final String USERNAME = "Hasaker";
    private static final String PASSWORD = "5523";

    private static final ConnectionFactory factory = new ConnectionFactory();
    private Connection connection;

    private ConnectionFactory() {}

    public static ConnectionFactory getInstance() {
        return factory;
    }

    public Connection makeConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
