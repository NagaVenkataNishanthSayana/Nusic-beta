package com.example.Nusic.service;

import com.example.Nusic.model.Admin;

public interface AdminService {
    void addAdmin(Admin admin);

    Admin getAdmin(String email);
}
