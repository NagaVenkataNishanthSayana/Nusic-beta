package com.example.Nusic.DAO;

import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public class AlbumDAO extends DAO{
    public Album create(Album album) throws AlbumException {
        try {
            //save user object in the database
            begin();
            getSession().persist(album);
            commit();
            close();
            return album;

        } catch (HibernateException e) {
            rollback();
            throw new AlbumException("Exception while creating album: " + e.getMessage());
        }
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
        }catch (HibernateException e) {
            rollback();
            throw new AlbumException("Exception while retrieving album: " + e.getMessage());
        }
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
        } catch (HibernateException e) {
            rollback();
            throw new AlbumException("Exception while retrieving all albums: " + e.getMessage());
        }
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
        }catch (HibernateException e){
            rollback();
            throw new AlbumException("Exception while retrieving Album by name:"+e.getMessage());
        }
    }

    public Album updateAlbum(Long id, Album album) throws AlbumException{
        try {
            begin();
            Album currAlbum=getSession().get(Album.class,id);

//            getSession().detach(currAlbum);
            if(album!=null){
                if(album.getAlbumName()!=null) currAlbum.setAlbumName(album.getAlbumName());
                if(album.getLeadArtist()!=null) currAlbum.setLeadArtist(album.getLeadArtist());
            }

//            getSession().merge(currAlbum);
//            Album updatedAlbum=getSession().merge(currAlbum);
            Set<Song> songs=currAlbum.getSongs();
            songs.size();
            commit();
            close();
            return currAlbum;
        }catch (HibernateException e){
            rollback();
            throw new AlbumException("Exception while updating the Album:"+e.getMessage());
        }catch(Exception e){
            rollback();
            throw new AlbumException("SQL or other exception:"+e.getMessage());
        }
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
//            session.merge(album);
            commit();
            close();
        }catch (HibernateException e){
            rollback();
            throw new AlbumException("Error while removing a song from Album"+e.getMessage());
        }
    }
}
