package com.example.Nusic.DAO;

import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class AlbumDAO extends DAO{
    public Album create(Album album) throws Exception {
        try {
            //save user object in the database
            begin();
            getSession().persist(album);
            commit();
            close();
            return album;

        } catch (HibernateException e) {
            rollback();
            throw new Exception();

        }

    }

    public Album getByAlbumId(Long id) throws Exception {

        try{
            begin();
            Album album= (Album) getSession().get(Album.class,String.valueOf(id));
            Set<Song> songsList=album.getSongs();
            commit();
            close();
            for(Song s:songsList){
                System.out.println(s.getSongName());
            }
            return album;
        }catch (HibernateException e) {
            rollback();
            System.out.println(e);
            throw new Exception("Issue with fetching");

        }
    }
}
