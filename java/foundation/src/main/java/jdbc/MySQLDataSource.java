package jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Logger;

public class MySQLDataSource implements DataSource {
    private final LinkedList<Connection> dataSource = new LinkedList<>();

    public MySQLDataSource() {
        for (int i = 0; i < 10; i++) {
            try {
                Connection connection = DBUtilities.getConnection();
                dataSource.add(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        final Connection connection = dataSource.removeFirst();

        return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(), (proxy, method, args) -> {
                    // Enhance 'close' method, make it release connection instead of closing connection
                    if (method.getName().equals("close")) {
                        releaseConnection(connection);
                        return null;
                    } else {
                        // Methods need not to be enhanced
                        return method.invoke(connection, args);
                    }
                });
    }

    private void releaseConnection(Connection connection) {
        dataSource.add(connection);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }
}
