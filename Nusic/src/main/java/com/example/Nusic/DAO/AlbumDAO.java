package com.example.Nusic.DAO;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import jakarta.persistence.EntityExistsException;
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
import java.util.List;
import java.util.Set;

@Repository
public class AlbumDAO extends DAO{
    public Album createAlbum(Album album) throws AlbumException {
        try {
            //save user object in the database
            begin();
            Album currAlbum= (Album) getSession().save(album);
            commit();
            close();
            return currAlbum;

        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("foreign key constraint")) {
                throw new ForeignKeyConstraintException("Foreign key constraint violation", e);
            } else {
                throw new DuplicateEntryException("Album already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("Album Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (EntityExistsException e) {
            throw new DuplicateEntryException("Album already exists", e);
        } catch (PersistenceException e) {
            throw new DatabaseException("Error executing database operation", e);
        }catch (Exception e) {
            rollback();
            throw new AlbumException("Exception while creating album: " + e.getMessage());
        }
        return null;
    }

    public Album getByAlbumId(Long id) throws AlbumException {

        try{
            begin();
            Session session=getSession();
            Album album= session.get(Album.class,String.valueOf(id));
            Set<Song> songsList = album.getSongs();
            songsList.size();
            commit();
            close();

            return album;
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
            throw new EntityNotFoundException("Album Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (Exception e){
            rollback();
            throw new AlbumException("Exception while retrieving album: " + e.getMessage());
        }
        return null;
    }


    public List<Album> getAllAlbums() throws AlbumException {

        try{
            begin();
            List<Album> albums=getSession().createNativeQuery(
                            "select * from albums ",Album.class
                    ).setMaxResults(10)
                    .getResultList();

            commit();
            close();
            for(Album album:albums){
                album.setSongs(null);
            }
            return albums;
        } catch (ConstraintViolationException e) {
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
            throw new EntityNotFoundException("Album Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (Exception e){
            rollback();
            throw new AlbumException("Exception while retrieving album: " + e.getMessage());
        }
        return null;
    }

    public Album getAlbumByName(String albumName) throws AlbumException {
        try{
            begin();
            String hql="FROM Album where albumName=:albumName";
            Query query=getSession().createQuery(hql,Album.class);
            query.setParameter("albumName",albumName);
            Album album= (Album) query.getSingleResult();
            Set<Song> songs=album.getSongs();
            songs.size();
            commit();
            close();
            return album;
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
            throw new EntityNotFoundException("Album Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (Exception e){
            rollback();
            throw new AlbumException("Error retrieving Album Details by name", e);
        }
        return null;
    }

    public Album updateAlbum(Long id, Album album) throws AlbumException {
        try {
            begin();
            Album currAlbum=getSession().get(Album.class,id);
            if(album!=null){
                if(album.getAlbumName()!=null) currAlbum.setAlbumName(album.getAlbumName());
                if(album.getLeadArtist()!=null) currAlbum.setLeadArtist(album.getLeadArtist());
            }

            Set<Song> songs=currAlbum.getSongs();
            songs.size();
            commit();
            close();
            return currAlbum;
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("Duplicate entry")) {
                throw new DuplicateEntryException("Album already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("Album Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("Album Details not found", e);
        }
        catch (Exception e){
            rollback();
            throw new AlbumException("Error retrieving Album Details by email", e);
        }
        return null;
    }

    public void deleteAlbum(Long id) throws AlbumException {
        try {
            begin();
            Album album=getSession().get(Album.class,id);
            if(album!=null){
                getSession().remove(album);
            }
            commit();
            close();
        }catch (HibernateException e){
            rollback();
            throw new AlbumException("Exception while deleting album:"+e.getMessage());
        }
    }

    public void deleteSongFromAlbum(Long albumId, Long songId) throws AlbumException {
        try {
            begin();
            Session session=getSession();
            Album album=session.get(Album.class,albumId);
            Song song=session.get(Song.class,songId);
            Set<Song> songs=album.getSongs();
            songs.remove(song);
            album.setSongs(songs);
            session.remove(song);
            commit();
            close();
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("Duplicate entry")) {
                throw new DuplicateEntryException("Album already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("Album Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("Album Details not found", e);
        }
        catch (Exception e){
            rollback();
            throw new AlbumException("Error retrieving Album Details by email", e);
        }
    }
}
