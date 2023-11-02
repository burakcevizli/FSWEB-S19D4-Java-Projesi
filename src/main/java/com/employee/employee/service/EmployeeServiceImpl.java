package com.employee.employee.service;

import com.employee.employee.dao.EmployeeRepository;
import com.employee.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        return null;
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }

    @Override
    public List<Employee> findBySalary(int salary) {
        return employeeRepository.findBySalary(salary);
    }

    @Override
    public List<Employee> findByOrder() {
        return employeeRepository.findByOrder();
    }

    @Override
    public Employee save(Employee employee) {
        Employee existingEmployee = findByEmail(employee.getEmail());
        if(existingEmployee != null){
            return null;
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(int id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
        return employee;
    }
}
