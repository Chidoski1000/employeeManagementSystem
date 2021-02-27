package com.example.employeemanagement2.services.serviceImpl;

import com.example.employeemanagement2.models.Attendance;
import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.repositories.AttendanceRepository;
import com.example.employeemanagement2.repositories.EmployeeRepository;
import com.example.employeemanagement2.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    AttendanceRepository attendanceRepository;
    EmployeeRepository employeeRepository;


    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }



    @Override
    public Map<String, String> addAttendance(Employee employee) {
        Map<String, String> response = new HashMap<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.toString().split("T")[0];
        String time = localDateTime.toString().split("T")[1];
        String hour = time.split(":")[0];
        String newStartTime = hour.replace(hour,"08:00:00.000");
        String newEndTime = hour.replace(hour,"17:00:00.000");
        String newStartDate = date+"T"+newStartTime;
        String newEndDate = date+"T"+newEndTime;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime localStartDate = LocalDateTime.parse(newStartDate, dateTimeFormatter);
        LocalDateTime localEndDate = LocalDateTime.parse(newEndDate, dateTimeFormatter);

        if (localDateTime.isBefore(localStartDate)){
            response.put("beforeTime", "Too early to mark attendance");
        }else if (localDateTime.isAfter(localEndDate)){
            response.put("afterTime", "Too late to mark attendance");
        }else{
            Optional<Attendance> employeeAttendance = attendanceRepository.findByEmployeeAndTimeAppliedIsBetween(employee, localStartDate, localEndDate);
            if (employeeAttendance.isEmpty()) {
                Optional<Employee> employeedb = employeeRepository.findById(employee.getId());
                if (employeedb.isPresent()) {
                    Attendance attendance = new Attendance();
                    attendance.setEmployee(employeedb.get());
                    attendance.setAttendanceMark(true);
                    attendanceRepository.save(attendance);
                    response.put("success", "Attendance marked successfully");
                }else{
                    response.put("notFound", "Employee does not exists");
                }
            }else{
                response.put("alreadyMarked", "Attendance already marked");
            }
        }
        return response;
    }

    @Override
    public List<Attendance> getAllDailyAttendance() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.toString().split("T")[0];
        String time = localDateTime.toString().split("T")[1];
        String hour = time.split(":")[0];
        String newStartTime = hour.replace(hour,"00:00:00.000");
        String newEndTime = hour.replace(hour,"23:59:59.999");
        String newStartDate = date+"T"+newStartTime;
        String newEndDate = date+"T"+newEndTime;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime localStartDate = LocalDateTime.parse(newStartDate, dateTimeFormatter);
        LocalDateTime localEndDate = LocalDateTime.parse(newEndDate, dateTimeFormatter);
       return attendanceRepository.findAllByTimeAppliedBetween(localStartDate, localEndDate);
    }

    @Override
    public List<Attendance> getAttendanceByEmployeeId(Employee employee) {
        return attendanceRepository.findAllByEmployee(employee);
    }
}
