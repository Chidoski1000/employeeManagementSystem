package com.example.employeemanagement2.services;

import com.example.employeemanagement2.models.Attendance;
import com.example.employeemanagement2.models.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    void removeEmployee(Long employeeId);

    void updateEmployee(Employee employee, Long employeeId);

    Employee getEmployeeByEmail(String email);

    Employee getEmployeeById(Long employeeId);

    Employee getEmployeeByEmailAndPassword(String email, String password);

    List<Employee> getAllEmployee();

    void updateEmployeePassword(Employee employee, Long employeeId);

}
