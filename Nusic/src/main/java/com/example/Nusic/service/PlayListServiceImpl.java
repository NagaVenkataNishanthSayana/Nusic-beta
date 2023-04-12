package com.example.Nusic.service;

import com.example.Nusic.DAO.PlayListDAO;
import com.example.Nusic.model.PlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListServiceImpl implements PlayListService {

    @Autowired
    PlayListDAO playListDAO;

    @Override
    public PlayList getPlaylistById(Long id) {
        return playListDAO.getPlaylistById(id);
    }

    @Override
    public PlayList createPlaylist(PlayList playlist) {
        return playListDAO.createPlaylist(playlist);
    }

    @Override
    public PlayList updatePlaylist(Long id, PlayList playlist) {
        return playListDAO.updatePlaylist(id,playlist);
    }

    @Override
    public void deletePlaylist(Long id) {
        playListDAO.deletePlaylist(id);
    }
}
