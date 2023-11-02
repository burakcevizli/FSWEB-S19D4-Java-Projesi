package com.employee.employee.dao;

import com.employee.employee.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeRepositoryTest {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private void createEmployee(String firstName , String lastName , String email , int salary) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setSalary(salary);
        employeeRepository.save(employee);
    }
    @BeforeEach
    void setUp(){
        createEmployee("Burak","Cevizli","burakcevizli@gmail.com",30000);
        createEmployee("Ahmet","Kasim","ahmet@gmail.com",45000);
        createEmployee("Süleyman","Lastvegas","lastvegas@gmail.com",70000);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void canFindByEmail() {
        Optional<Employee> foundEmployee = employeeRepository.findByEmail("burakcevizli@gmail.com");
        assertNotNull(foundEmployee.get());
        assertEquals("Burak", foundEmployee.get().getFirstName());
        assertEquals(30000, foundEmployee.get().getSalary());

    }

    @Test
    void canFindBySalary(){
        List<Employee> employeeList = employeeRepository.findBySalary(30000);
        assertEquals(2,employeeList.size());
        assertEquals("Süleyman", employeeList.get(0).getFirstName());

    }


    @Test
    void findByOrder(){
        List<Employee> employeeListWithOrder = employeeRepository.findByOrder();
        assertEquals("Cevizli",employeeListWithOrder.get(0).getLastName());
    }
}
