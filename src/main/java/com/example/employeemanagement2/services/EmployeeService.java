package com.example.employeemanagement2.services;

import com.example.employeemanagement2.models.Attendance;
import com.example.employeemanagement2.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee addEmployee(Employee employee);

    void removeEmployee(Long employeeId);

    void updateEmployee(Employee employee, Long employeeId);

    Employee getEmployeeByEmail(String email);

    Employee getEmployeeById(Long employeeId);

    Employee getEmployeeByEmailAndPassword(String email, String password);

    List<Employee> getAllEmployee();

}
