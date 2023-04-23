package com.example.Nusic.service;

import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;

public interface AdminService {
    Admin createAdmin(Admin admin) throws AdminException;

    Admin getAdminByEmail(String email) throws AdminException;

    Admin updateAdminDetails(Long id, Admin admin) throws AdminException;
}
