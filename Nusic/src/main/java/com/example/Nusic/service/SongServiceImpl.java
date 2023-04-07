package com.example.Nusic.service;

import com.example.Nusic.DAO.AlbumDAO;
import com.example.Nusic.DAO.SongDAO;
import com.example.Nusic.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongDAO songDAO;

    @Override
    public Song getSongById(Long id) {
        return null;
    }

    @Override
    public Song addSong(Long albumId, Song song) throws Exception {

        return songDAO.addSong(albumId,song);
    }

    @Override
    public Song updateSong(Long id, Song song) {
        return null;
    }

    @Override
    public void deleteSong(Long id) {

    }

    @Override
    public List<Song> getSongByPlayListId(Long id) {
        return null;
    }

    @Override
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    @Override
    public List<Song> getSongByAlbumId(Long id) {
        return null;
    }
}
