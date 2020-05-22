package demo.dao;

import demo.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface UserDao {
    public void save(Connection connection, User user);
    public void update(Connection connection, User user);
    public void delete(Connection connection, User user);
    public ResultSet get(Connection connection, String uname);
    public List<User> queryAllUsers(Connection connection);
}
