package com.example.employeemanagement2.services;

import com.example.employeemanagement2.models.Admin;


public interface AdminService {

    Admin getAdminByEmail(String email);

    Admin getAdminByEmailAndPassword(String adminEmail, String password);
}
