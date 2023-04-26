package com.example.Nusic.DAO;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.Admin;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Repository
public class AdminDAO extends DAO{
    public Admin getAdminByEmail(Admin admin) throws AdminException, DatabaseConnectionException {
        try {
            begin();
            String hql="FROM Admin WHERE email=:email";
            Query query= getSession().createQuery(hql, Admin.class);
            query.setParameter("email",admin.getEmail());
            Admin currAdmin= (Admin) query.getSingleResult();
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
            throw new EntityNotFoundException("Admin Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
        }catch (Exception e){
            rollback();
            throw new AdminException("Error retrieving User Details by email", e);
        }
        return null;
    }

    public Admin createAdmin(Admin admin) throws AdminException, DatabaseConnectionException {
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
            }
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
        }catch (Exception e){
            rollback();
            throw new AdminException("Error retrieving User Details by email", e);
        }
        return null;
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

        } catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("Duplicate entry")) {
                throw new DuplicateEntryException("User already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("User Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("User Details not found", e);
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
        }catch (Exception e){
            rollback();
            throw new AdminException(e.getMessage(),e);
        }
        return null;
    }
}
