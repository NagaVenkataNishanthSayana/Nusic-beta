package com.example.Nusic.DAO;

import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
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
                    )
                    .getResultList();
            for(Album album:albums){
                album.setSongs(null);
            }
            commit();
            close();

            return albums;
        }catch (HibernateException e) {
            rollback();
            throw new AlbumException("Exception while retrieving all albums: " + e.getMessage());

        }
    }
}
