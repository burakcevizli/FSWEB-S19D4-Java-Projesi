package com.employee.employee.dao;

import com.employee.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {

    @Query("SELECT e FROM Employee e WHERE e.email=:email")
    Optional<Employee> findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary ORDER BY e.salary DESC")
    List<Employee> findBySalary(int salary);

    @Query("SELECT e FROM Employee e ORDER BY e.lastName ASC")
    List<Employee> findByOrder();

}
