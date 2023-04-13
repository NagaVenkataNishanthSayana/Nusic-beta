package com.example.Nusic.service;

import com.example.Nusic.DAO.AdminDAO;
import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminDAO adminDAO;
    @Override
    public void addAdmin(Admin admin) throws AdminException {
        adminDAO.addAdmin(admin);
    }

    @Override
    public Admin getAdmin(String email) throws AdminException {
        return adminDAO.getAdmin(email);
    }
}
