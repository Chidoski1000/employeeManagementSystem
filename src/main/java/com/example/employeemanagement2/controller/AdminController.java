package com.example.employeemanagement2.controller;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.Salary;
import com.example.employeemanagement2.services.AttendanceService;
import com.example.employeemanagement2.services.EmployeeService;
import com.example.employeemanagement2.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

        EmployeeService employeeService;
        AttendanceService attendanceService;
        SalaryService salaryService;

    @Autowired
    public AdminController(EmployeeService employeeService, AttendanceService attendanceService, SalaryService salaryService) {
        this.employeeService = employeeService;
        this.attendanceService = attendanceService;
        this.salaryService = salaryService;
    }

    @GetMapping("/homepage")
    public String viewHomePage(Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        model.addAttribute("listEmployees", employeeService.getAllEmployee());
        return "admin-dashboard";
    }

    @GetMapping("/newemployeeform")
    public String showNewEmployeeForm(Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        //create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @GetMapping("/formforupdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

//        //set employee as a model attribute to pre-populate the form
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @PostMapping("/update-employee/{id}")
    public String updateEmployee(@ModelAttribute("employee") Employee employee, @PathVariable( value = "id") long id, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        //save employee to database
        employeeService.updateEmployee(employee, id);
        return "redirect:/homepage";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";


        employeeService.addEmployee(employee);
        return "redirect:/homepage";
    }

    @GetMapping("/deleteemployee/{id}")
    public String deleteEmployee(@PathVariable ( value = "id") long id, Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        this.employeeService.removeEmployee(id);
        return "redirect:/homepage";
    }

    @GetMapping("/viewemployee/{id}")
    public String viewEmployeeAttendance(@PathVariable ( value = "id") long id, Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("attendanceList", attendanceService.getAttendanceByEmployeeId(employee));
        model.addAttribute("employee", employee);
        return "adminempl-viewpage";
    }

    @GetMapping("/formforAddSalary/{id}")
    public String updateSalary(@PathVariable (value = "id") long id, HttpSession session, Model model){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";


        Salary salary = new Salary();
        model.addAttribute("salary", salary);
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "addSalary";
    }

    @PostMapping("/save-salary/{id}")
    public String saveSalary(@ModelAttribute("salary") Salary salary, @PathVariable (value = "id") long id, HttpSession session){
        salaryService.saveSalary(id, salary);
        return "redirect:/homepage";
    }
}
