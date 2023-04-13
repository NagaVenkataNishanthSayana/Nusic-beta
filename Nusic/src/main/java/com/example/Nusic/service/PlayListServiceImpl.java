package com.example.Nusic.service;

import com.example.Nusic.DAO.PlayListDAO;
import com.example.Nusic.exception.PlayListException;
import com.example.Nusic.model.PlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService {

    @Autowired
    PlayListDAO playListDAO;

    @Override
    public PlayList getPlaylistById(Long id) throws PlayListException {
        return playListDAO.getPlaylistById(id);
    }

    @Override
    public PlayList createPlaylist(PlayList playlist, Long userId) throws PlayListException {
        return playListDAO.createPlaylist(playlist,userId);
    }

    @Override
    public PlayList updatePlaylist(Long id, PlayList playlist) {
        return playListDAO.updatePlaylist(id,playlist);
    }

    @Override
    public void deletePlaylist(Long id) throws PlayListException {
        playListDAO.deletePlaylist(id);
    }

    @Override
    public List<PlayList> getAllPlaylists() {
        return playListDAO.getAllPlaylists();
    }

    @Override
    public PlayList addSongToPlayList(Long songId, Long PlayListId) throws PlayListException {
        return playListDAO.addSongToPlayList(songId,PlayListId);
    }
}
