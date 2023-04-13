package com.example.Nusic.service;

import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;

public interface AdminService {
    void addAdmin(Admin admin) throws AdminException;

    Admin getAdmin(String email) throws AdminException;
}
