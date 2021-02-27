package com.example.employeemanagement2.controller;

import com.example.employeemanagement2.models.Admin;
import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.services.AdminService;
import com.example.employeemanagement2.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    private EmployeeService employeeService;
    private AdminService adminService;

    @Autowired
    public LoginController(EmployeeService employeeService, AdminService adminService) {
        this.employeeService = employeeService;
        this.adminService = adminService;
    }

    @GetMapping(path = "/")
    public String login(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("invalid", null);

        return "login";
    }

    @PostMapping("/")
    public String login (HttpSession session, Admin admin, Model model, Employee employee) {
        Admin thisAdmin = adminService.getAdminByEmailAndPassword(admin.getEmail(), admin.getPassword());
        Employee thisEmployee = employeeService.getEmployeeByEmailAndPassword(employee.getEmail(), employee.getPassword());
        if (thisAdmin == null && thisEmployee == null) {
            //error message if email does not exist in database
            model.addAttribute("invalid", "User does not exist. Check login details or register.");
            return "login";
        } else if(thisAdmin != null) {
            session.setAttribute("admin", thisAdmin);
            return "redirect:/homepage";
        }else{
            model.addAttribute("name", thisEmployee.getFirstname());
            session.setAttribute("employee", thisEmployee);
            return "redirect:/employee";
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
