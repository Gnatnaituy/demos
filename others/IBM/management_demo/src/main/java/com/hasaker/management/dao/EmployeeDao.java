package com.hasaker.management.dao;

import com.hasaker.management.bean.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {

    int add(Employee employee);

    int update(Employee employee);

    int delete(int empno);

    List<Map<String, Object>> queryAll();

    Employee query(int empno);
}
