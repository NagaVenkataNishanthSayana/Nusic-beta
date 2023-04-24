package com.example.Nusic.DAO;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Repository
public class UserDAO extends DAO{

    public UserDAO() {
    }

    public User createUser(User user) throws UserException {
        try {
            //save user object in the database
            begin();
            User currUser= (User) getSession().save(user);
            commit();
            close();

            return currUser;
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("foreign key constraint")) {
                throw new ForeignKeyConstraintException("Foreign key constraint violation", e);
            } else {
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
        }catch (EntityExistsException e) {
            rollback();
            throw new DuplicateEntryException("User already exists", e);
        } catch (PersistenceException e) {
            rollback();
            throw new DatabaseException("Error executing database operation", e);
        }catch (Exception e){
            rollback();
            throw new UserException("Error creating user", e);
        }
        return null;
    }

    public User updateUserDetails(Long id, User user) throws UserException {
        try{
            begin();
            User currUser=getSession().get(User.class,id);
            if(currUser==null)
            if(user!=null){
                String fistName=user.getFistName();
                String lastName=user.getLastname();
                String password= user.getPassword();
                if(fistName!=null)currUser.setFistName(fistName);
                if(lastName!=null)currUser.setLastname(lastName);
                if(password!=null)currUser.setPassword(password);
            }
            commit();
            close();
            currUser.setPlaylists(null);
            return currUser;
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
        }catch (Exception e){
            rollback();
            throw new UserException("Error retrieving updating User Details", e);
        }
        return null;
    }

    public User getUserByEmail(User user) throws UserException, DatabaseConnectionException {
        try{
            begin();
            String hql="FROM User WHERE email=:email";
            Query query= getSession().createQuery(hql,User.class);
            query.setParameter("email",user.getEmail());
            User currUser= (User) query.getSingleResult();
            commit();
            close();
            currUser.setPlaylists(null);
            return currUser;
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
            throw new EntityNotFoundException("User Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (Exception e){
            rollback();
            throw new UserException("Error retrieving User Details by email", e);
        }
        return null;
    }
}
