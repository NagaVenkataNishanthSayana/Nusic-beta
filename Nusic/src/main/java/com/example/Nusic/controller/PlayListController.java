package com.example.Nusic.controller;

import com.example.Nusic.model.PlayList;
import com.example.Nusic.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlayListController {

    @Autowired
    private PlayListService playlistService;

    @GetMapping("/{id}")
    public PlayList getPlaylistById(@PathVariable Long id) {
        return playlistService.getPlaylistById(id);
    }

    @PostMapping("/")
    public PlayList createPlaylist(@RequestBody PlayList playlist) {
        return playlistService.createPlaylist(playlist);
    }

    @PutMapping("/{id}")
    public PlayList updatePlaylist(@PathVariable Long id, @RequestBody PlayList playlist) {
        return playlistService.updatePlaylist(id, playlist);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
    }
}