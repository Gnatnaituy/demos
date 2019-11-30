package com.hasaker.management.controller;


import com.hasaker.management.bean.Employee;
import com.hasaker.management.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public ModelAndView queryAll() {
        List<Map<String, Object>> employeeList = service.queryAll();

        return new ModelAndView("employee_list", "employeeList", employeeList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@ModelAttribute Employee employee) {

        return new ModelAndView("employee_add", "employee", employee);
    }

    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    public String doAdd(@ModelAttribute Employee employee) {
        service.add(employee);

        return "redirect:/emp/";
    }

    @RequestMapping(value = "/query/{empno}", method = RequestMethod.GET)
    public ModelAndView query(@PathVariable("empno") int empno) {
        Employee employee = service.query(empno);

        return new ModelAndView("employee_info", "employee", employee);
    }

    @RequestMapping(value = "update/{empno}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("empno") int empno) {
        Employee employee = service.query(empno);

        return new ModelAndView("employee_update", "employee", employee);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String doUpdate(@ModelAttribute Employee employee) {
        service.update(employee);

        return "redirect:/emp/query/" + employee.getEmpno();
    }

    @RequestMapping(value = "/delete/{empno}", method = RequestMethod.GET)
    public String delete(@PathVariable("empno") int empno) {
        service.delete(empno);

        return "redirect:/emp/";
    }
}
