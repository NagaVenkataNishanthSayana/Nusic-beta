package com.example.Nusic.controller;

import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.User;
import com.example.Nusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String add(@RequestBody User user) throws UserException {
        userService.saveUser(user);
        return "New User is created";
    }

    @GetMapping("/{email}")
    public String getUser(@PathVariable String email) throws UserException {
        return userService.getUser(email);

    }


}
