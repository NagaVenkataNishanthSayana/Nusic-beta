package com.example.Nusic.DAO;

import com.example.Nusic.exception.AdminException;
import com.example.Nusic.model.Admin;
import com.example.Nusic.model.Album;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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

    public Admin addAdmin(Admin admin) throws AdminException {
        try {
            begin();
            getSession().persist(admin);
            commit();
            close();
            return admin;
        }catch (HibernateException e){
            rollback();
            throw new AdminException("Error while creating admin",e);
        }
    }

    public Admin updateAdminDetails(Long id,Admin admin) throws AdminException {
        try{

            begin();
            Session session=getSession();
            Admin currAdmin=session.get(Admin.class,id);
            if(admin.getPassword()!=null) currAdmin.setPassword(admin.getPassword());
            commit();
            close();
            return currAdmin;

        }catch(HibernateException e){
            rollback();

            throw new AdminException("Exception while updating Admin Details:"+e.getMessage());

        }
    }
}
