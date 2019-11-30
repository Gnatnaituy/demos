package com.hasaker.management.dao.impl;

import com.hasaker.management.dao.EmployeeDao;
import com.hasaker.management.bean.Employee;
import com.hasaker.management.mapper.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int add(Employee employee) {
        String sql = "insert into employee(ename, job, mgr, hiredate, sal, comm, deptno)"
                + "values(:ename, :job, :mgr, :hiredate, :sal, :comm, :deptno)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(
                Objects.requireNonNull(jdbcTemplate.getDataSource()));

        return npjt.update(sql, new BeanPropertySqlParameterSource(employee));
    }

    @Override
    public int update(Employee employee) {
        String sql = "update employee set ename = ?, job = ?, mgr = ?, hiredate = ?, sal = ?,"
                + "comm = ?, deptno = ? where empno = ?";
        Object[] args = { employee.getEname(), employee.getJob(), employee.getMgr(), employee.getHiredate(),
                employee.getSal(), employee.getComm(), employee.getDeptno(), employee.getEmpno() };
        int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.DATE, Types.INTEGER, Types.INTEGER,
                Types.INTEGER, Types.INTEGER };

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int delete(int empno) {
        String sql = "delete from employee where empno = ?";
        Object[] args = { empno };
        int[] argTypes = { Types.INTEGER };

        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Map<String, Object>> queryAll() {
        String sql = "select * from employee";

        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Employee query(int empno) {
        String sql = "select * from employee where empno = ?";
        Object[] args = { empno };
        int[] argTypes = { Types.INTEGER };

        List<Employee> employees = jdbcTemplate.query(sql, args, argTypes, new EmployeeMapper());

        return employees != null && employees.size() > 0 ? employees.get(0) : null;
    }
}
