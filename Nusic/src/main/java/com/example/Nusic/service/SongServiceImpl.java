package com.example.Nusic.service;

import com.example.Nusic.model.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Override
    public Song getSongById(Long id) {
        return null;
    }

    @Override
    public Song createSong(Song song) {
        return null;
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
}
