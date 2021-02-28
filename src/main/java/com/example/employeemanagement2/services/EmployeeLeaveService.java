package com.example.employeemanagement2.services;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.myLeave;

import java.util.List;

public interface EmployeeLeaveService {
    //return type    // name  //input
    List<myLeave> getEmployeeLeaves(Long employeeId);

    List<myLeave> getAllEmployeeLeavesRecord();

    String addLeave(Employee employee);
}
