package com.example.Nusic.DAO;

import com.example.Nusic.model.User;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.HibernateException;
import com.example.Nusic.exception.UserException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends DAO{

    public UserDAO() {
    }

    public User create(User user) throws UserException {
        try {
            //save user object in the database
            begin();
            getSession().save(user);
            commit();
            close();

            return user;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new UserException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(User user) throws UserException {
        try {
            //save user object in the database
            begin();
            getSession().delete(user);
            commit();

        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new UserException("Exception while deleting user: " + e.getMessage());
        }
    }
}
