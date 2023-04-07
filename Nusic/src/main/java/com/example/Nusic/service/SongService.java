package com.example.Nusic.service;


import com.example.Nusic.model.Song;

import java.util.List;

public interface SongService {


    Song getSongById(Long id);

    Song addSong(Long albumId, Song song) throws Exception;

    Song updateSong(Long id, Song song);

    void deleteSong(Long id);

    List<Song> getSongByPlayListId(Long id);

    List<Song> getAllSongs();

    List<Song> getSongByAlbumId(Long id);
}
