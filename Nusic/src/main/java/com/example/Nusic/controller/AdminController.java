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
    public String addAdmin(@RequestBody Admin admin) throws AdminException {
        adminService.addAdmin(admin);
        return "Admin Registered";
    }

    @GetMapping("/{email}")
    public Admin getAdmin(@PathVariable String email) throws AdminException {
        return adminService.getAdmin(email);
    }
}
