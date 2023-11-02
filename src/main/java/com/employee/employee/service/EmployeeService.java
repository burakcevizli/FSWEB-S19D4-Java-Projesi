package com.employee.employee.service;

import com.employee.employee.entity.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);
    Employee findByEmail(String email);
    List<Employee> findBySalary(int salary);
    List<Employee> findByOrder();
    Employee save(Employee employee);
    Employee delete(int id);
}
