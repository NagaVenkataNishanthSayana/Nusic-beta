package com.example.Nusic.DAO;

import com.example.Nusic.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDAO extends DAO{


    public SongDAO() {
    }

    public List<String> getAllSongs(){
        List<String> songs = getSession().createNativeQuery(
                        "select songPath from Song ",
                        String.class)
                .getResultList();

        return songs;
    }
}
