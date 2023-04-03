package com.example.Nusic.controller;

import com.example.Nusic.model.Admin;
import com.example.Nusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/")
    public String addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
        return "Admin Registered";
    }

    @GetMapping("/{email}")
    public Admin getAdmin(@PathVariable String email){
        return adminService.getAdmin(email);
    }
}
