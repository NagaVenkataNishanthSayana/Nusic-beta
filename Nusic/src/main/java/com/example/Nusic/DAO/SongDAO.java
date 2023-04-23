package com.example.Nusic.DAO;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
public class SongDAO extends DAO{


    public SongDAO() {
    }

    @Autowired
    AlbumDAO albumDAO;

    public List<Song> getAllSongs() throws SongException {
        try{
            begin();
            List<Song> songs = getSession().createNativeQuery(
                            "select * from Songs ",Song.class
                    )
                    .getResultList();
            for(Song s:songs){
                System.out.println(s);
            }
            commit();
            close();
            return songs;
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
            throw new EntityNotFoundException("Songs Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (Exception e){
            rollback();
            throw new SongException("Exception while retrieving all Songs: " + e.getMessage());

        }
        return null;
    }

    public Song addSongToAlbum(Long albumId, Song song) throws SongException {

        try{
            begin();
            Session session=getSession();
            Album album=session.get(Album.class,albumId);
            song.setAlbum(album);
            album.getSongs().add(song);
            session.save(song);
            commit();
            close();
            return song;
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("foreign key constraint")) {
                throw new ForeignKeyConstraintException("Foreign key constraint violation", e);
            } else {
                throw new DuplicateEntryException("Song already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("Song Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (EntityExistsException e) {
            rollback();
            throw new DuplicateEntryException("Song already exists", e);
        } catch (PersistenceException e) {
            rollback();
            throw new DatabaseException("Error executing database operation", e);
        }catch (Exception e){
            rollback();
            throw new SongException("Exception while adding Song: " + e.getMessage());
        }
        return null;
    }

    public Song getSongById(Long id) throws SongException {
        try{
            begin();
            Song song = getSession().get(Song.class,id);
            commit();
            close();
            return song;
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
            throw new EntityNotFoundException("Song Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (Exception e){
            rollback();
            throw new SongException("Error while fetching Song By Name:"+e.getMessage());
        }
        return null;
    }


    public Song getSongByName(String songName) throws SongException {
        try{
            begin();
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Song> cr = cb.createQuery(Song.class);
            Root<Song> root = cr.from(Song.class);
            //Criterion restriction can be implemented as well
            //Criterion restriction = Restrictions.eq("fieldName", value);
            //select single column data using restrictions
            //criteriaQuery.select(projection).where(restriction);
            cr.select(root).where(cb.like(root.get("songName"), songName));
            Query<Song> query = getSession().createQuery(cr);
            Song song=query.getSingleResult();
            commit();
            close();

            return song;

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
            throw new EntityNotFoundException("Song Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (Exception e){
            rollback();
            throw new SongException("Error while fetching Song By Name:"+e.getMessage());
        }
        return null;
    }

    public Song updateSong(Long id, Song song) throws SongException {
        try{
            begin();
            Song currSong=getSession().get(Song.class,id);
            if(song!=null){
                if (song.getSongName()!=null) currSong.setSongName(song.getSongName());
                if(song.getSongPath()!=null) currSong.setSongPath(song.getSongPath());
            }
            currSong.setSongName(song.getSongName());
            currSong.setSongPath(song.getSongPath());
            commit();
            close();
            return currSong;
        } catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("Duplicate entry")) {
                throw new DuplicateEntryException("Song already exists", e);
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
            throw new EntityNotFoundException("Song Details not found", e);
        }
        catch (Exception e){
            rollback();
            throw new SongException("Exception while updating Song:"+e.getMessage());
        }

        return null;

    }
}
