package com.example.Nusic.service;

import com.example.Nusic.DAO.AdminDAO;
import com.example.Nusic.exception.AdminException;
import com.example.Nusic.exception.PasswordMismatchException;
import com.example.Nusic.model.Admin;
import com.example.Nusic.model.User;
import com.example.Nusic.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminDAO adminDAO;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Admin createAdmin(Admin admin) throws AdminException {
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        return adminDAO.createAdmin(admin);
    }

    @Override
    public Admin validateAdmin(Admin admin) throws AdminException {
        Admin currAdmin=adminDAO.getAdminByEmail(admin);
        if(!passwordEncoder.matches(admin.getPassword(),currAdmin.getPassword())){
            throw new PasswordMismatchException("Password MisMatch");
        }
        return currAdmin;
    }

    @Override
    public Admin updateAdminDetails(Long id, Admin admin) throws AdminException {
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        return adminDAO.updateAdminDetails(id,admin);
    }
}
