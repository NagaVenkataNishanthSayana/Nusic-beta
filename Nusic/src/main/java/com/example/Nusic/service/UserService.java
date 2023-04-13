package com.example.Nusic.service;

import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User saveUser(User student) throws UserException;

    public String getUserByEmail(User user);

    void updateUserDetails(Long id);
}
