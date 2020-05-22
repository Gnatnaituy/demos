package demo.dao.impl;

import demo.dao.UserDao;
import demo.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(Connection connection, User user) {
        String sql = "INSERT INTO t_user (uname, pwd) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUname());
            statement.setString(2, user.getPwd());
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Connection connection, User user) {
        String sql = "UPDATE t_user SET uname = ?, pwd = ? WHERE uid = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUname());
            statement.setString(2, user.getPwd());
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Connection connection, User user) {
        String sql = "DELETE FROM t_user WHERE uid = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getUid());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet get(Connection connection, String uname) {
        String sql = "SELECT uid, uname, pwd FROM t_user WHERE uname = ?";
        ResultSet resultSet = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, uname);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public List<User> queryAllUsers(Connection connection) {
        String sql = "SELECT uid, uname, pwd FROM t_user";
        ResultSet resultSet = null;
        List<User> allUsers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
           resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (resultSet != null) {
            while (true) {
                try {
                    if (!resultSet.next()) break;
                    User user = new User(resultSet.getInt("uid"),
                            resultSet.getString("uname"),
                            resultSet.getString("pwd"));
                    allUsers.add(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return allUsers;
    }
}
