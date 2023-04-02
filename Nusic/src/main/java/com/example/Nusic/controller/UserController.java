package com.example.Nusic.controller;

import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.User;
import com.example.Nusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) throws UserException {
        userService.saveUser(user);
        return "New User is created";
    }


}
