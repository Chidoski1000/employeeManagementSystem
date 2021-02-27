package com.example.employeemanagement2.repositories;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.Salary;
import com.example.employeemanagement2.models.myLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<myLeave, Long> {
    List<myLeave> findAllByEmployee(Employee employee);
}
