package com.example.Nusic.DAO;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.Song;
import com.example.Nusic.model.User;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PlayListDAO extends DAO{
    public PlayList getPlaylistById(Long id) throws PlayListException {
        try{
            begin();
            PlayList playList=getSession().get(PlayList.class,id);
            Set<Song> songs =playList.getSongs();
            songs.size();
            commit();
            close();
            return playList;
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
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("PlayList or Song Details not found", e);
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
            throw new PlayListException("Error while fetching Playlist by Id"+e.getMessage());
        }
        return null;
    }


    public PlayList createPlaylist(PlayList playlist, Long userId) throws PlayListException {

        try{
            begin();
            Session session=getSession();
            playlist.setUser(session.get(User.class,userId));
            session.persist(playlist);
            commit();
            close();
            return playlist;
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("foreign key constraint")) {
                throw new ForeignKeyConstraintException("Foreign key constraint violation", e);
            } else {
                throw new DuplicateEntryException("Playlist already exists", e);
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
            throw new DuplicateEntryException("Playlist already exists", e);
        } catch (PersistenceException e) {
            rollback();
            throw new DuplicateEntryException("Playlist already exists", e);
        }catch (Exception e){
            rollback();
            throw new PlayListException("Error while creating a playlist"+e.getMessage());
        }
        return null;
    }

    public PlayList updatePlaylist(Long id, PlayList playList) throws PlayListException {
        try {
            begin();
            Session session=getSession();
            PlayList currPlayList=session.get(PlayList.class,id);
            if(playList.getPlayListName()!=null){
                currPlayList.setPlayListName(playList.getPlayListName());
                currPlayList.setPlayListName(playList.getPlayListName());
            }
            currPlayList.getSongs().size();
            commit();
            close();

            return currPlayList;
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("Duplicate entry")) {
                throw new DuplicateEntryException("PlayList already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("PlayList Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("PlayList Details not found", e);
        }catch (PersistenceException e){
            rollback();
            throw new DuplicateEntryException("PlayList Name already exists", e);
        }
        catch (Exception e){
            rollback();
            throw new PlayListException("Error updating PlayList Details", e);
        }
        return null;
    }

    public void deletePlaylist(Long id) throws PlayListException {
        try{
            begin();
            PlayList playList=getSession().get(PlayList.class,id);

            if(playList!=null){
                getSession().remove(playList);
            }
            commit();
            close();
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("Duplicate entry")) {
                throw new DuplicateEntryException("PlayList already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("PlayList Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("PlayList Details not found", e);
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
        }catch (Exception e){
            rollback();
            throw new PlayListException("Error while deleting the playlist "+e.getMessage());
        }
    }

    public List<PlayList> getAllPlaylists() throws PlayListException {
        try {
            begin();
            Session session=getSession();
            String hql="FROM PlayList";
            Query query=session.createQuery(hql,PlayList.class).setMaxResults(10);
            List<PlayList> playLists=query.getResultList();
            commit();
            close();
            for (PlayList playList:playLists){
                playList.setSongs(null);
            }
            return playLists;
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
            throw new EntityNotFoundException("PlayList Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("PlayList or Song Details not found", e);
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
        }catch (Exception e){
            rollback();
            throw new PlayListException("Error while getting all playlists:"+e.getMessage());
        }
        return null;
    }

    public PlayList addSongToPlayList(Song song, Long playListId) throws PlayListException {
        try{
            begin();
            Session session=getSession();
            PlayList playList=session.get(PlayList.class,playListId);
            Song newSong=session.get(Song.class,song.getId());
            Set<Song> songsSet=playList.getSongs();
            songsSet.add(newSong);
            playList.setSongs(songsSet);
            session.persist(playList);
            commit();
            close();

            return playList;
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
            throw new EntityNotFoundException("PlayList Details not found", e);
        } catch (HibernateException e) {
            rollback();
            if (e.getCause() instanceof SQLException) {
                throw new UnknownSqlException("Unknown SQL exception", e);
            }
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("PlayList or Song Details not found", e);
        }catch(PersistenceException e){
            rollback();
            throw new UnknownSqlException("Duplicate Entry",e);
        }catch (Exception e){
            rollback();
            throw new PlayListException("Error while adding song to Playlist:"+e.getMessage());
        }
        return null;
    }

    public PlayList getPlayListByName(String playlistName) throws PlayListException {
        try {
            begin();
            Session session=getSession();
            String hql="From PlayList where playListName=:playListName";
            Query query=session.createQuery(hql,PlayList.class);
            PlayList currPlayList= (PlayList) query.getSingleResult();
            Set<Song> songs=currPlayList.getSongs();
            songs.size();
            commit();
            close();
            return currPlayList;

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
            throw new EntityNotFoundException("PlayList Details not found", e);
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("PlayList or Song Details not found", e);
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
            throw new PlayListException("Error while getting playList by name:"+playlistName);
        }
        return null;
    }

    public PlayList removeSongPlayList(Long id, Long songsId) throws PlayListException {
        try {
            begin();
            Session session=getSession();
            PlayList playList=session.get(PlayList.class,id);
            Song currSong=session.get(Song.class,songsId);
            Set<Song> songsSet=playList.getSongs();
            songsSet.remove(currSong);
            playList.setSongs(songsSet);
            session.persist(playList);
            commit();
            close();
            return playList;
        }catch (ConstraintViolationException e) {
            rollback();
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException && cause.getMessage().contains("Duplicate entry")) {
                throw new DuplicateEntryException("PlayList already exists", e);
            }
        } catch (JDBCConnectionException e) {
            rollback();
            throw new DatabaseConnectionException("Unable to connect to the database", e);
        } catch (StaleStateException e) {
            rollback();
            throw new OptimisticLockException("Optimistic lock exception", e);
        } catch (ObjectNotFoundException e) {
            rollback();
            throw new EntityNotFoundException("PlayList Details not found", e);
        }catch (NullPointerException e){
            rollback();
            throw new EntityNotFoundException("PlayList or Song Details not found", e);
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
            throw new PlayListException("Error while removing song from PlayList:"+e.getMessage());
        }
        return null;
    }
}
