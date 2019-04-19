package com.hasaker.spring_boot_demo.mapper;

import com.hasaker.spring_boot_demo.bean.User;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new User(rs.getInt("id"),
                rs.getString("username"),
                rs.getInt("age"),
                rs.getString("password"));
    }
}
