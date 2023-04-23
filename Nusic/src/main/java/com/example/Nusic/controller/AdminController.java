package com.example.Nusic.controller;

import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;
import com.example.Nusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/")
    public ModelAndView createAdmin(@RequestBody Admin admin)  {
        Admin currAdmin=null;
        try {
            currAdmin=adminService.createAdmin(admin);
            return new ModelAndView("SingUp","admin",currAdmin);
        } catch (AdminException e) {
            return new ModelAndView("ErrorPage");
        }
    }

    @PostMapping("/")
    public ModelAndView validateAdminByEmail(@RequestBody String email)  {
        Admin admin=null;
        try {
            admin=adminService.getAdminByEmail(email);
            return new ModelAndView("Home","admin",admin);
        } catch (AdminException e) {
            return new ModelAndView("ErrorPage");
        }

    }

    @PutMapping("/{id}")
    public ModelAndView updateAdminDetails(@PathVariable Long id,@RequestBody Admin admin){

        Admin currAdmin=null;
        try {
            currAdmin=adminService.updateAdminDetails(id,admin);
            return new ModelAndView("Home","admin",currAdmin);
        } catch (AdminException e) {
            return new ModelAndView("ErrorPage");
        }

    }
}
