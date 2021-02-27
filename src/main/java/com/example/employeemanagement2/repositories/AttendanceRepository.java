package com.example.employeemanagement2.repositories;

import com.example.employeemanagement2.models.Attendance;
import com.example.employeemanagement2.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//    Optional<Attendance> findByEmployeeAndTimeMarkedBetween(Employee employee, LocalDateTime start, LocalDateTime end);
    Optional<Attendance> findByEmployeeAndTimeAppliedIsBetween(Employee employee, LocalDateTime start, LocalDateTime end);
    List<Attendance> findAllByTimeAppliedBetween(LocalDateTime start, LocalDateTime end);
    List<Attendance> findAllByEmployee(Employee employee);
}
