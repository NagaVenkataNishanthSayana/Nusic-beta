package com.example.Nusic.DAO;

import com.example.Nusic.model.Song;
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
}
