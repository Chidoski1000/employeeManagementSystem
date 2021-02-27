package com.example.employeemanagement2.services;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.myLeave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeLeaveService {
    //return type    // name  //input
    List<myLeave> getEmployeeLeaves(Long employeeId);

    List<myLeave> getAllEmployeeLeavesRecord();

    String addLeave(Employee employee);
}
