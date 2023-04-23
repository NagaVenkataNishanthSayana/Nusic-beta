package com.example.Nusic.service;

import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;

public interface AdminService {
    Admin createAdmin(Admin admin) throws AdminException;

    Admin validateAdmin(Admin admin) throws AdminException;

    Admin updateAdminDetails(Long id, Admin admin) throws AdminException;
}
