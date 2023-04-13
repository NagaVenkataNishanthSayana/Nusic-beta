package com.example.Nusic.controller;

import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;
import com.example.Nusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/")
    public String createAdmin(@RequestBody Admin admin) throws AdminException {
        adminService.createAdmin(admin);
        return "Admin Registered";
    }

    @PostMapping("/")
    public Admin validateAdminByEmail(@RequestBody String email) throws AdminException {
        return adminService.getAdminByEmail(email);
    }

    @PutMapping("/{id}")
    public String updateAdminDetails(@PathVariable Long id){
        adminService.updateAdminDetails(id);
        return "Admin Details updated";
    }
}
