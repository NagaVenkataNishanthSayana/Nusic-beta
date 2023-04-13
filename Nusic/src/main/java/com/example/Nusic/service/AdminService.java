package com.example.Nusic.service;

import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;

public interface AdminService {
    void createAdmin(Admin admin) throws AdminException;

    Admin getAdminByEmail(String email) throws AdminException;

    void updateAdminDetails(Long id);
}
