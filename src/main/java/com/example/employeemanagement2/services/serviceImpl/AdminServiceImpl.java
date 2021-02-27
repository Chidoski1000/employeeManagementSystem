package com.example.employeemanagement2.services.serviceImpl;

import com.example.employeemanagement2.models.Admin;
import com.example.employeemanagement2.repositories.AdminRepository;
import com.example.employeemanagement2.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin getAdminByEmail(String adminEmail) {
        Optional<Admin> admin = adminRepository.findByEmail(adminEmail);
        if (admin.isPresent()) return admin.get();
        return null;
    }

    @Override
    public Admin getAdminByEmailAndPassword(String adminEmail, String password) {
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(adminEmail, password);
        if (admin.isPresent()) return admin.get();
        return null;
    }
}
