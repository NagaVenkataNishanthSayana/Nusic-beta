package com.example.Nusic.DAO;

import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO extends DAO{
    public Admin getAdmin(String email) throws AdminException {
        try {
            begin();
            Admin admin=getSession().find(Admin.class,email);

            return admin;
        }catch (HibernateException e){
            throw new AdminException("",e);
        }
    }

    public void addAdmin(Admin admin) throws AdminException {
        try {
            begin();
            getSession().persist(admin);
            commit();
            close();
        }catch (HibernateException e){
            throw new AdminException("",e);
        }
    }
}
