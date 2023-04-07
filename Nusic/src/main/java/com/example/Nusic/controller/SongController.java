package com.example.Nusic.controller;


import com.example.Nusic.model.Song;
import com.example.Nusic.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id) {
        return songService.getSongById(id);
    }

    @GetMapping("/{PlayList_id}")
    public List<Song> getSongByPlayListId(@PathVariable Long id) {
        return songService.getSongByPlayListId(id);
    }

    @GetMapping("/{Album_id}")
    public List<Song> getSongByAlbumId(@PathVariable Long id) {
        return songService.getSongByAlbumId(id);
    }

    @GetMapping("/AllSongs")
    public List<Song> getAllSongs(){return songService.getAllSongs();}

    @PostMapping("/{albumId}")
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
