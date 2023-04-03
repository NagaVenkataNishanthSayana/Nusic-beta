package com.example.Nusic.service;


import com.example.Nusic.model.Song;

import java.util.List;

public interface SongService {


    Song getSongById(Long id);

    Song createSong(Song song);

    Song updateSong(Long id, Song song);

    void deleteSong(Long id);

    List<Song> getSongByPlayListId(Long id);

    List<String> getAllSongs();
}
