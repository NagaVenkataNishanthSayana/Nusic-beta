package com.example.Nusic.controller;

import com.example.Nusic.exception.PlayListException;
import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.User;
import com.example.Nusic.service.PlayListService;
import com.example.Nusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayListService playListService;

    @PostMapping("/")
    public String createUser(@RequestBody User user) throws UserException {
        userService.saveUser(user);
        return "New User is created";
    }

    @PostMapping("/{id}/playlists")
    public PlayList createPlaylist(@PathVariable Long id,@RequestBody PlayList playlist) throws PlayListException {
        return playListService.createPlaylist(playlist,id);
    }

    @PostMapping("/login")
    public String validateUserByEmail(@RequestBody User user) throws UserException {
        return userService.getUserByEmail(user);

    }

    @PutMapping("/{id}")
    public String updateUserDetails(@PathVariable Long id){
        userService.updateUserDetails(id);
        return "User Details updated";
    }


}
