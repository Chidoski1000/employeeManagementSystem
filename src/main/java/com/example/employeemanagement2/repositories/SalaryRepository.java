package com.example.employeemanagement2.repositories;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findAllByEmployee(Employee employee);
}
