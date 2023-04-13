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

    @GetMapping("/{songName}")
    public Song getSongByName(@PathVariable String songName){
        return songService.getSongByName(songName);
    }

    @GetMapping("/")
    public List<Song> getAllSongs() throws SongException {return songService.getAllSongs();}

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song song) {
        return songService.updateSong(id, song);
    }


}
