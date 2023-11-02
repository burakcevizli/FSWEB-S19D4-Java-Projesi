package com.employee.employee.controller;

import com.employee.employee.entity.Employee;
import com.employee.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PostMapping("/order")
    public List<Employee> findByOrder(){
        return employeeService.findByOrder();
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable int id){
        return employeeService.delete(id);
    }
}
