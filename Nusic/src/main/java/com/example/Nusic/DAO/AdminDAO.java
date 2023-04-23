package com.example.Nusic.DAO;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.Admin;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Repository
public class AdminDAO extends DAO{
    public Admin getAdminByEmail(String email) throws AdminException, DatabaseConnectionException {
        try {
            begin();
            Admin admin=getSession().find(Admin.class,email);

            return admin;
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("foreign key constraint")) {
                throw new ForeignKeyConstraintException("Foreign key constraint violation", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("Admin Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            } else {
                throw new AdminException("Error creating admin", e);
            }
        }
        return null;
    }

    public Admin addAdmin(Admin admin) throws AdminException, DatabaseConnectionException {
        try {
            begin();
            getSession().persist(admin);
            commit();
            close();
            return admin;
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("foreign key constraint")) {
                throw new ForeignKeyConstraintException("Foreign key constraint violation", e);
            } else {
                throw new DuplicateEntryException("Admin already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("Entity not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            } else {
                throw new AdminException("Error creating admin", e);
            }
        }
    }

    public Admin updateAdminDetails(Long id,Admin admin) throws AdminException, DatabaseConnectionException {
        try{

            begin();
            Session session=getSession();
            Admin currAdmin=session.get(Admin.class,id);
            if(admin.getPassword()!=null) currAdmin.setPassword(admin.getPassword());
            commit();
            close();
            return currAdmin;

        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("foreign key constraint")) {
                throw new ForeignKeyConstraintException("Foreign key constraint violation", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("Entity not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            } else {
                throw new AdminException("Error creating admin", e);
            }
        }
        return admin;
    }
}
