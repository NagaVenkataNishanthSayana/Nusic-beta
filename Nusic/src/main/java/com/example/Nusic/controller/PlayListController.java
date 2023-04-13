package com.example.Nusic.controller;

import com.example.Nusic.exception.PlayListException;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    @GetMapping("/{id}")
    public PlayList getPlaylistById(@PathVariable Long id) throws PlayListException {
        return playListService.getPlaylistById(id);
    }

    @GetMapping("/")
    public List<PlayList> getAllPlaylist(@PathVariable Long id) {
        return playListService.getAllPlaylists();
    }

    @GetMapping("/{playlistName}")
    public PlayList getPlayListByName(@PathVariable String playlistName){
        return playListService.getPlayListByName(playlistName);
    }

    @PostMapping("/{id}/songs")
    public PlayList addSongToPlayList(@PathVariable Long id, @RequestBody Song song) throws PlayListException {
        return playListService.addSongToPlayList(song, id);
    }

    @PutMapping("/{id}")
    public PlayList updatePlaylist(@PathVariable Long id, @RequestBody PlayList playlist) {
        return playListService.updatePlaylist(id, playlist);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id) throws PlayListException {
        playListService.deletePlaylist(id);
    }
}