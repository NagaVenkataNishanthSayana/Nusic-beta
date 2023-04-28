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
            Long albumId= (Long) getSession().save(album);
            commit();
            close();
            album.setId(albumId);
            return album;

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
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
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
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
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
        }catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
        }catch (Exception e){
            rollback();
            throw new AlbumException("Exception while retrieving album: " + e.getMessage());
        }
        return null;
    }

    public List<Album> getAlbumsByName(String albumName) throws AlbumException {
        try{
            begin();
            if(albumName.length()<3) throw new StringIndexOutOfBoundsException();
            String hql="FROM Album where albumName LIKE:albumName";
            Query query=getSession().createQuery(hql,Album.class);
            query.setParameter("albumName",albumName + "%");
            query.setMaxResults(10);
            List<Album> albums= query.getResultList();
            commit();
            close();
            for(Album album:albums){
                System.out.println(album.getAlbumName());
                album.setSongs(null);
            }
            return albums;
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
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
        }catch (StringIndexOutOfBoundsException e){
            throw new LengthException("The length of Album name should be at least 3",e);
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
            if(currAlbum==null){
                commit();
                close();
                throw new EntityNotFoundException("Album Details not found with this ID");
            }
            currAlbum=  getSession().merge(currAlbum);
            if(album!=null){
                if(album.getAlbumName()!=null) currAlbum.setAlbumName(album.getAlbumName());
                if(album.getLeadArtist()!=null) currAlbum.setLeadArtist(album.getLeadArtist());
            }
            getSession().merge(currAlbum);
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
        }catch (PersistenceException e) {
            rollback();
            throw new EntityNotFoundException("Album Details not found", e);
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
            String sqlQuery="DELETE FROM playlist_tracks WHERE song_id = :songId";
            Query query = getSession().createNativeQuery(sqlQuery);
            Set<Song> songs=null;
            if(album!=null) songs=album.getSongs();
            if(songs!=null){
                for(Song song:songs){
                    query.setParameter("songId", song.getId());
                    int deletedRows = query.executeUpdate();
                }
            }
            if(album!=null){
                getSession().remove(album);
            }else{
                commit();
                close();
                throw new EntityNotFoundException("Album Details not found with this ID");
            }
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
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Album Details not found with this ID");
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("Album Details not found", e);
        }catch (PersistenceException e) {
            rollback();
            e.printStackTrace();
            throw new EntityNotFoundException("Unkown SQL Error", e);
        }catch (Exception e){
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
            String sqlQuery="DELETE FROM playlist_tracks WHERE song_id = :songId";
            Query query = session.createNativeQuery(sqlQuery);
            query.setParameter("songId", songId);
            int deletedRows = query.executeUpdate();
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
        }catch (PersistenceException e) {
            rollback();
            throw new EntityNotFoundException("Song Not Found in this Album", e);
        }catch (Exception e){
            rollback();
            throw new AlbumException("Error retrieving Album Details by email", e);
        }
    }
}
