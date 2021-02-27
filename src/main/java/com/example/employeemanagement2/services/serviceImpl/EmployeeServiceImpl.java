package com.example.employeemanagement2.services.serviceImpl;

import com.example.employeemanagement2.models.Employee;
import com.example.employeemanagement2.repositories.EmployeeRepository;
import com.example.employeemanagement2.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void removeEmployee(Long employeeId) {
        employeeRepository.delete(employeeRepository.findById(employeeId).get());
    }

    @Override
    public void updateEmployee(Employee employee, Long employeeId) {
        Optional<Employee> name = employeeRepository.findById(employeeId);
        name.get().setFirstname(employee.getFirstname());
        name.get().setLastName(employee.getLastName());
        name.get().setEmail(employee.getEmail());
        employeeRepository.save(name.get());
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByEmail(String email) {

        return employeeRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Employee getEmployeeByEmailAndPassword(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) return employee.get();
        return null;
    }

}
