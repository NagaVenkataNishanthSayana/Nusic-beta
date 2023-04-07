package com.example.Nusic.DAO;

import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDAO extends DAO{


    public SongDAO() {
    }


    public List<Song> getAllSongs(){
        List<Song> songs = getSession().createNativeQuery(
                        "select * from Song ",Song.class
                        )
                .getResultList();
        for(Song s:songs){
            System.out.println(s);
        }
        return songs;
    }

    public Song addSong(Long albumId, Song song) throws Exception {

        try{
            begin();
            Session session=getSession();
            song.setAlbum((Album) session.get(Album.class,String.valueOf(albumId)));
            session.persist(song);
            commit();
            close();
            return song;
        }catch (HibernateException e){
            throw new Exception();
        }
    }
}
