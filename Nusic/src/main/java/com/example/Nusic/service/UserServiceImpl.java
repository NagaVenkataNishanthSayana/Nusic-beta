package com.example.Nusic.service;

import com.example.Nusic.DAO.UserDAO;
import com.example.Nusic.exception.DatabaseConnectionException;
import com.example.Nusic.exception.PasswordMismatchException;
import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.User;
import com.example.Nusic.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) throws UserException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userDAO.createUser(user);
    }

    @Override
    public User validateUser(User user) throws UserException, PasswordMismatchException, DatabaseConnectionException {
        User currUser=userDAO.getUserByEmail(user);
        if(!passwordEncoder.matches(user.getPassword(),currUser.getPassword())){
            throw new PasswordMismatchException("Password MisMatch");
        }
        return currUser;
    }

    @Override
    public User updateUserDetails(Long id, User user) throws UserException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userDAO.updateUserDetails(id,user);
    }
}
