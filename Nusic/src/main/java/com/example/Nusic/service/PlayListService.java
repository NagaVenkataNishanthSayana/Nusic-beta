package com.example.Nusic.service;

import com.example.Nusic.exception.PlayListException;
import com.example.Nusic.model.PlayList;

import java.util.List;

public interface PlayListService {
    PlayList getPlaylistById(Long id) throws PlayListException;

    PlayList createPlaylist(PlayList playlist, Long id) throws PlayListException;

    PlayList updatePlaylist(Long id, PlayList playlist);

    void deletePlaylist(Long id) throws PlayListException;

    List<PlayList> getAllPlaylists();

    PlayList addSongToPlayList(Long song, Long id) throws PlayListException;
}
