package com.example.employeemanagement2.controller;

import com.example.employeemanagement2.models.Attendance;
import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.services.AttendanceService;
import com.example.employeemanagement2.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AttendanceController {
    AttendanceService attendanceService;
    EmployeeService employeeService;


    public AttendanceController(AttendanceService attendanceService, EmployeeService employeeService) {
        this.attendanceService = attendanceService;
        this.employeeService = employeeService;
    }

    @PostMapping("/mark-attendance")
    public String markAttendance(HttpSession session, RedirectAttributes redirectAttributes){
        Employee employee = (Employee) session.getAttribute("employee");
        Map<String, String> response = attendanceService.addAttendance(employee);
        if (response.containsKey("alreadyMarked")){
            redirectAttributes.addFlashAttribute("marked", response.get("alreadyMarked"));
            return "redirect:/employee";
        }else if (response.containsKey("notFound")){
            redirectAttributes.addFlashAttribute("notFound", response.get("notFound"));
            return "redirect:/employee";
        }else if (response.containsKey("beforeTime")){
            redirectAttributes.addFlashAttribute("beforeTime", response.get("beforeTime"));
            return "redirect:/employee";
        }else if (response.containsKey("afterTime")){
            redirectAttributes.addFlashAttribute("afterTime", response.get("afterTime"));
            return "redirect:/employee";
        }else if(response.containsKey("success")) {
            redirectAttributes.addFlashAttribute("success", response.get("success"));
            return "redirect:/employee";
        }
        return "empl-dashboard";
    }


    @GetMapping("/daily-attendance")
    public String dailyAttendance(Model model, HttpSession session){
        Object userObj = session.getAttribute("admin");
        if (userObj == null) return "redirect:/";

        List<Attendance> listAttendance = attendanceService.getAllDailyAttendance();
        List<Employee> listEmployeeAttendance = listAttendance.stream().map(Attendance::getEmployee).collect(Collectors.toList());
        model.addAttribute("listEmployeesAttendance", listEmployeeAttendance);
        return "admin-daily-attendance";
    }


    @GetMapping("/employee-attendance/{id}")
    public String employeeAttendance(@PathVariable(value = "id") long id, Model model, HttpSession session){
        Object userObj = session.getAttribute("employee");
        if (userObj == null) return "redirect:/";

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("attendanceList", attendanceService.getAttendanceByEmployeeId(employee));
        model.addAttribute("employee", employee);
        return "empl-attendance";
    }
}
