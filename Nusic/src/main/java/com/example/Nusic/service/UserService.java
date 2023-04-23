package com.example.Nusic.service;

import com.example.Nusic.exception.DatabaseConnectionException;
import com.example.Nusic.exception.PasswordMismatchException;
import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User saveUser(User student) throws UserException;

    public User validateUser(User user) throws UserException, PasswordMismatchException, DatabaseConnectionException;

    User updateUserDetails(Long id, User user) throws UserException;
}
