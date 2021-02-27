package com.example.employeemanagement2.services.serviceImpl;

//import com.example.employeemanagementtask.Models.Salary;
import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.Salary;
import com.example.employeemanagement2.repositories.SalaryRepository;
import com.example.employeemanagement2.services.EmployeeService;
import com.example.employeemanagement2.services.SalaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    EmployeeService employeeService;
    SalaryRepository salaryRepository;

    @Autowired
    public SalaryServiceImpl(EmployeeService employeeService, SalaryRepository salaryRepository) {
        this.employeeService = employeeService;
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<Salary> getSalaries(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return salaryRepository.findAllByEmployee(employee);
    }

    @Override
    public void saveSalary(Long id, Salary salary) {
        Employee employee = employeeService.getEmployeeById(id);
        Salary newSalary = new Salary();
        newSalary.setAmount(salary.getAmount());
        newSalary.setMonth(salary.getMonth());
        newSalary.setEmployee(employee);
        salaryRepository.save(newSalary);
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }
}
