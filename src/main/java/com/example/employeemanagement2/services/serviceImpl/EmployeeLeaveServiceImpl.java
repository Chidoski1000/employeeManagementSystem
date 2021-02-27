package com.example.employeemanagement2.services.serviceImpl;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.myLeave;
import com.example.employeemanagement2.repositories.EmployeeLeaveRepository;
import com.example.employeemanagement2.repositories.EmployeeRepository;
import com.example.employeemanagement2.services.EmployeeLeaveService;
import com.example.employeemanagement2.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

    private EmployeeLeaveRepository employeeLeaveRepository;
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeLeaveServiceImpl(EmployeeLeaveRepository employeeLeaveRepository, EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeLeaveRepository = employeeLeaveRepository;
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<myLeave> getEmployeeLeaves(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return employeeLeaveRepository.findAllByEmployee(employee);
    }

    @Override
    public String addLeave(Employee employee) {
        Optional<Employee> employeedb = employeeRepository.findById(employee.getId());
        myLeave leave = new myLeave();
        leave.setEmployee(employeedb.get());
        leave.setLeaveStatus(true);
        employeeLeaveRepository.save(leave);
        return "Successful";
    }

    @Override
    public List<myLeave> getAllEmployeeLeavesRecord() {
        return employeeLeaveRepository.findAll();
    }
}
