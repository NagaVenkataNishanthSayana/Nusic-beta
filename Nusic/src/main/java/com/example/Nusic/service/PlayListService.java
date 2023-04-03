package com.example.Nusic.service;

import com.example.Nusic.model.PlayList;

public interface PlayListService {
    PlayList getPlaylistById(Long id);

    PlayList createPlaylist(PlayList playlist);

    PlayList updatePlaylist(Long id, PlayList playlist);

    void deletePlaylist(Long id);
}
