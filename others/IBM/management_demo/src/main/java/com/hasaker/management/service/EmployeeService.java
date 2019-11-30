package com.hasaker.management.service;

import com.hasaker.management.bean.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public int add(Employee employee);

    public int update(Employee employee);

    public int delete(int empno);

    public List<Map<String, Object>> queryAll();

    public Employee query(int empno);
}
