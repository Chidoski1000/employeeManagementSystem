package com.example.employeemanagement2.controller;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.services.EmployeeLeaveService;
import com.example.employeemanagement2.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LeaveController {
    EmployeeService employeeService;
    EmployeeLeaveService employeeLeaveService;

    @Autowired
    public LeaveController(EmployeeService employeeService, EmployeeLeaveService employeeLeaveService) {
        this.employeeService = employeeService;
        this.employeeLeaveService = employeeLeaveService;
    }

    @GetMapping("/admin_empl-leave/{id}")
    public String viewEmployeeLeaves(@PathVariable( value = "id") long id, Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("leaveList", employeeLeaveService.getEmployeeLeaves(id));
        model.addAttribute("employee", employee);
        return "adminempl-viewpage";
    }

    @PostMapping("/apply-leave")
    public String applyLeave(HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        employeeLeaveService.addLeave(employee);
        return "empl-dashboard";
    }

    @GetMapping("/admin_empl-leave")
    public String adminEmployeeLeave(HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        return "adminempl-leave";
    }

    @GetMapping("/leave-records")
    public String viewSalaryRecord(Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        model.addAttribute("leaveRecords", employeeLeaveService.getAllEmployeeLeavesRecord());
        return "records";
    }
}
