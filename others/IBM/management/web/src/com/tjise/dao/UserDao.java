package com.tjise.dao;

import com.tjise.model.User;
import com.tjise.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    /**
     * Get a PreparedStatement Instance
     * @param sql the SQL Expression
     * @return PreparedStatement
     */
    private PreparedStatement getStatement(String sql) throws SQLException {
        Connection connection = DBUtil.getConnection();
        return connection.prepareStatement(sql);
    }


    /**
     * Set PreparedStatement args, put null if you want a specified arg to null
     */
    private void setStatementArgs(PreparedStatement s, Integer uid, String uname, String pwd,
                                  String sex, Integer age, LocalDate birth) throws SQLException {
        int index = 1;
        if (uid != null) {
            s.setInt(index, uid);
            index++;
        }
        if (uname != null) {
            s.setString(index, uname);
            index++;
        }
        if (pwd != null) {
            s.setString(index, pwd);
            index++;
        }
        if (sex != null) {
            s.setString(index, sex);
            index++;
        }
        if (age != null) {
            s.setInt(index, age);
            index++;
        }
        if (birth != null) {
            s.setDate(index, Date.valueOf(birth));
        }
    }


    /**
     * Add a user with given parameters
     * @param user User instance
     * @return true if add successful, otherwise false
     */
    public boolean addUser(User user) throws SQLException {
        String sql ="INSERT INTO t_user(uname, pwd, sex, age, birth) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = getStatement(sql);
        setStatementArgs(statement, null, user.getUname(), user.getPwd(),
                user.getSex(), user.getAge(), user.getBirth());

        return statement.execute();
    }


    /**
     * Update user information
     * @param user A user instance that some information has changed but uid
     * @return true if successful
     */
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE t_user SET uname=?, pwd=?, sex=?, age=?, birth=? WHERE uid=?";
        PreparedStatement statement = getStatement(sql);
        setStatementArgs(statement, null, user.getUname(), user.getPwd(),
                user.getSex(), user.getAge(), user.getBirth());
        statement.setInt(6, user.getUid());

        return statement.execute();
    }


    /**
     * Get a user information from database using given uid
     * @param uid A User object's uid
     * @return null if there's no such uid in database else a User object with information
     *         queried from database by uid
     */
    public User queryUser(int uid) throws SQLException {
        String sql = "SELECT * FROM t_user WHERE uid=?";
        PreparedStatement statement = getStatement(sql);
        setStatementArgs(statement, uid, null, null, null, null, null);
        ResultSet res = statement.executeQuery();

        return (!res.next()) ? null : new User(res.getInt("uid"), res.getString("uname"),
                res.getString("pwd"), res.getString("sex"),
                res.getInt("age"), res.getDate("birth").toLocalDate());
    }


    /**
     * Get a user information from database using given uname
     * @param uname A User object's uid
     * @return null if there's no such uname in database else a User object with information
     *         queried from database by uname
     */
    public User queryUser(String uname) throws SQLException {
        String sql = "SELECT * FROM t_user WHERE uname=?";
        PreparedStatement statement = getStatement(sql);
        setStatementArgs(statement, null, uname, null, null, null, null);
        ResultSet res = statement.executeQuery();

        return (!res.next()) ? null : new User(res.getInt("uid"), res.getString("uname"),
                res.getString("pwd"), res.getString("sex"),
                res.getInt("age"), res.getDate("birth").toLocalDate());
    }


    /**
     * Get all users in database
     * @return ArrayList instance with type User
     */
    public List<User> queryAllUser() throws SQLException {
        String sql = "SELECT * FROM t_user";
        PreparedStatement statement = getStatement(sql);
        ResultSet res = statement.executeQuery();
        List<User> users = new ArrayList<>();

        while (res.next()) {
            users.add(new User(res.getInt("uid"), res.getString("uname"),
                    res.getString("pwd"), res.getString("sex"),
                    res.getInt("age"), res.getDate("birth").toLocalDate()));
        }

        return users;
    }

    /**
     * Delete a user by given uid
     */
    public boolean deleteUser(int uid) throws SQLException {
        String sql = "DELETE FROM t_user WHERE uid=?";
        PreparedStatement statement = getStatement(sql);
        statement.setInt(1, uid);

        return statement.execute();
    }
}
