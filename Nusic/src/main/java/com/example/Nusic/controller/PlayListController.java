package com.example.Nusic.controller;

import com.example.Nusic.exception.PlayListException;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PlayList>> getAllPlaylist(@PathVariable Long id) {
        List<PlayList> playLists=null;
        try {
            playLists= playListService.getAllPlaylists();
            return ResponseEntity.ok(playLists);
        } catch (PlayListException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/")
    public PlayList getPlayListByName(@RequestParam String playlistName){
        return playListService.getPlayListByName(playlistName);
    }

    @PostMapping("/{id}/songs")
    public PlayList addSongToPlayList(@PathVariable Long id, @RequestBody Song song) throws PlayListException {
        return playListService.addSongToPlayList(song, id);
    }

    @PutMapping("/{id}/songs/{songsId}")
    public ResponseEntity<PlayList> updatePlaylist(@PathVariable Long id, @RequestBody PlayList playlist) {
        try {
            PlayList currPlayList=null;
             currPlayList=playListService.updatePlaylist(id, playlist);
             return ResponseEntity.ok(currPlayList);
        } catch (PlayListException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}/songs/{songsId}")
    public ResponseEntity<PlayList> removeSongFromPlayList(@PathVariable Long id, @PathVariable Long songsId){
        PlayList currPlayList=null;
        try {
            currPlayList=playListService.removeSongFromPlayList(id,songsId);
            return ResponseEntity.ok(currPlayList);
        } catch (PlayListException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id) throws PlayListException {
        playListService.deletePlaylist(id);
    }
}