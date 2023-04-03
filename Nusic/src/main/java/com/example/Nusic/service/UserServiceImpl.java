package com.example.Nusic.service;

import com.example.Nusic.DAO.UserDAO;
import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Override
    public User saveUser(User user) throws UserException {
        return userDAO.create(user);
    }

    @Override
    public String getUser(String email) {
        return null;
    }
}
