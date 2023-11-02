package com.employee.employee.service;

import com.employee.employee.dao.EmployeeRepository;
import com.employee.employee.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void findAll(){
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    void findByEmail(){
        given(employeeRepository.findByEmail("dogan@test.com")).willReturn(Optional.empty());
        assertNull(employeeService.findByEmail("dogan@test.com"));

        Employee employee = new Employee();
        employee.setEmail("dogan@test.com");
        given(employeeRepository.findByEmail("dogan@test.com")).willReturn(Optional.of(employee));
        assertNotNull(employee);
    }

    @Test
    void save(){
        Employee employee = new Employee();
        employee.setEmail("deneme@test.com");
        given(employeeRepository.findByEmail("deneme@test.com")).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);
        Employee savedEmployee = employeeService.save(employee);
        assertNotNull(savedEmployee);
        verify(employeeRepository).save(employee);
    }

    @Test
    void canSaveEmployee(){
        Employee employee = new Employee();
        employee.setFirstName("Burak");
        employee.setLastName("Cevizli");
        employee.setEmail("burakcevizli@gmail.com");
        employee.setSalary(50000);
        employeeService.save(employee);
        verify(employeeRepository).save(employee);
    }

}
