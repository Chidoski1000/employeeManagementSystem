package com.example.employeemanagement2.controller;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.services.EmployeeService;
import com.example.employeemanagement2.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class SalaryController {
    EmployeeService employeeService;
    SalaryService salaryService;

    @Autowired
    public SalaryController(EmployeeService employeeService, SalaryService salaryService) {
        this.employeeService = employeeService;
        this.salaryService = salaryService;
    }

    @GetMapping("/admin_empl-salary/{id}")
    public String viewEmployeeSalary(@PathVariable( value = "id") long id, Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("salaryList", salaryService.getSalaries(id));
        model.addAttribute("employee", employee);
        return "adminempl-viewpage";
    }

    @GetMapping("/employee-salary/{id}")
    public String employeeSalary(@PathVariable(value = "id") long id,Model model, HttpSession session){
        Object userObj = session.getAttribute("employee");
        if (userObj == null) return "redirect:/";

        Employee emp  = (Employee)(userObj);
        model.addAttribute("salaryList", salaryService.getSalaries(id));
        model.addAttribute("thisEmployee", emp);
        return "empl-dashboard";
    }

    @GetMapping("/salary-records")
    public String viewSalaryRecord(Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        model.addAttribute("salaryRecords", salaryService.getAllSalaries());
        return "records";
    }

}
