package com.hasaker.spring_boot_demo.dao.impl;

import com.hasaker.spring_boot_demo.bean.User;
import com.hasaker.spring_boot_demo.dao.UserDao;
import com.hasaker.spring_boot_demo.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Objects;


@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int add(User user) {
        String sql = "insert into users(username, age, password) values(:username, :age, :password)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
                Objects.requireNonNull(jdbcTemplate.getDataSource()));

        return npjt.update(sql, new BeanPropertySqlParameterSource(user));
    }

    @Override
    public int update(User user) {
        String sql = "update users set username = ?, age = ?, password = ? where id = ?";
        Object[] args = {user.getUsername(), user.getAge(), user.getPassword(), user.getId()};
        int[] argTypes = {Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER};

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int deleteByUsername(String username) {
        String sql = "delete from users where username = ?";

        return jdbcTemplate.update(sql, new Object[]{username}, new int[Types.VARCHAR]);
    }

    @Override
    public List<User> queryAll() {
        String sql = "select * from users";

        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from users where username = ?";
        Object[] args = {username};
        int[] argTypes = {Types.VARCHAR};
        List<User> userList = this.jdbcTemplate.query(sql, args, argTypes, new UserMapper());

        return userList != null && userList.size() > 0 ? userList.get(0) : null;
    }
}
