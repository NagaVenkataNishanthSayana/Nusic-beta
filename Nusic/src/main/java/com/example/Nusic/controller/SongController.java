package com.example.Nusic.controller;


import com.example.Nusic.exception.SongException;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id) throws SongException {
        return songService.getSongById(id);
    }

    //Playlist
    @GetMapping("/playlists/{playList_id}")
    public List<Song> getSongByPlayListId(@PathVariable Long id) {
        return songService.getSongByPlayListId(id);
    }

    //Album
    @GetMapping("/albums/{album_id}")
    public List<Song> getSongByAlbumId(@PathVariable Long id) {
        return songService.getSongByAlbumId(id);
    }

    @GetMapping("/")
    public List<Song> getAllSongs() throws SongException {return songService.getAllSongs();}

    //Album
    @PostMapping("/albums/{albumId}")
    public Song addSong(@PathVariable Long albumId ,@RequestBody Song song) throws Exception {
        return songService.addSong(albumId,song);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song song) {
        return songService.updateSong(id, song);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
    }

}
