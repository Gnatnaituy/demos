package com.hasaker.management.service.impl;

import com.hasaker.management.dao.EmployeeDao;
import com.hasaker.management.bean.Employee;
import com.hasaker.management.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public int add(Employee employee) {
        return employeeDao.add(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public int delete(int empno) {
        return employeeDao.delete(empno);
    }

    @Override
    public List<Map<String, Object>> queryAll() {
        return employeeDao.queryAll();
    }

    @Override
    public Employee query(int empno) {
        return employeeDao.query(empno);
    }
}
