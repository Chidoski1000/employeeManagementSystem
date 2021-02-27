package com.example.employeemanagement2.services;


import com.example.employeemanagement2.models.Attendance;
import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.models.myLeave;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public interface AttendanceService {

//    Employee getEmployeeById(Long employeeId);
    Map<String, String> addAttendance(Employee employee);

    List<Attendance> getAllDailyAttendance();

    List<Attendance> getAttendanceByEmployeeId(Employee employee);

//    void updateAttendance(Attendance attendance, Employee employee);
}
