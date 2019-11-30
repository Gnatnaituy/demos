package com.hasaker.management.mapper;

import com.hasaker.management.bean.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                rs.getInt("empno"),
                rs.getString("ename"),
                rs.getString("job"),
                rs.getInt("mgr"),
                rs.getDate("hiredate"),
                rs.getInt("sal"),
                rs.getInt("comm"),
                rs.getInt("deptno")
        );
    }
}
