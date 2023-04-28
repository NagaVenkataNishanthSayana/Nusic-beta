package com.example.Nusic.service;

import com.example.Nusic.exception.PlayListException;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.Song;

import java.util.List;

public interface PlayListService {
    PlayList getPlaylistById(Long id) throws PlayListException;

    PlayList createPlaylist(PlayList playlist, Long id) throws PlayListException;

    PlayList updatePlaylist(Long id, PlayList playlist) throws PlayListException;

    void deletePlaylist(Long id) throws PlayListException;

    List<PlayList> getAllPlaylists(Long userId) throws PlayListException;

    PlayList addSongToPlayList(Song song, Long id) throws PlayListException;

    PlayList getPlayListByName(String playlistName) throws PlayListException;

    PlayList removeSongFromPlayList(Long id, Long songsId) throws PlayListException;
}
