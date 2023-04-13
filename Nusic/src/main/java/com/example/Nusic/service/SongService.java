package com.example.Nusic.service;


import com.example.Nusic.exception.SongException;
import com.example.Nusic.model.Song;

import java.util.List;

public interface SongService {


    Song getSongById(Long id) throws SongException;

    Song addSongToAlbum(Long albumId, Song song) throws Exception, SongException;

    Song updateSong(Long id, Song song);

    Song deleteSongFromAlbum(Long id, Song song);

    List<Song> getSongByPlayListId(Long id);

    List<Song> getAllSongs() throws SongException;

    List<Song> getSongByAlbumId(Long id);

    Song getSongByName(String songName);
}
