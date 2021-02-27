package com.example.employeemanagement2.services;

import com.example.employeemanagement2.models.Salary;

import java.util.List;

public interface SalaryService {

    List<Salary> getSalaries(Long EmployeeId);

    void saveSalary(Long id, Salary salary);

    List<Salary> getAllSalaries();

}
