package com.example.employeemanagement2.controller;

import com.example.employeemanagement2.models.Attendance;
import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.Salary;
import com.example.employeemanagement2.services.AttendanceService;
import com.example.employeemanagement2.services.EmployeeLeaveService;
import com.example.employeemanagement2.services.EmployeeService;
import com.example.employeemanagement2.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeLeaveService employeeLeaveService;
    private AttendanceService attendanceService;
    private SalaryService salaryService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeLeaveService employeeLeaveService,
                              AttendanceService attendanceService, SalaryService salaryService) {
        this.employeeService = employeeService;
        this.employeeLeaveService = employeeLeaveService;
        this.attendanceService = attendanceService;
        this.salaryService = salaryService;
    }


    @GetMapping("/employee")
    public String employee( Model model, HttpSession session){
        Object userObj = session.getAttribute("employee");
        if (userObj == null) return "redirect:/";
        Employee emp  = (Employee)(userObj);

        model.addAttribute("employee", new Employee());
        model.addAttribute("thisEmployee", emp);
        return "empl-dashboard";
    }


}