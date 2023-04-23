package com.example.Nusic.controller;


import com.example.Nusic.exception.SongException;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public Song getSongByName(@RequestParam(value = "songName") String songName) throws SongException {
        return songService.getSongByName(songName);
    }

    @GetMapping("/")
    public List<Song> getAllSongs() throws SongException {return songService.getAllSongs();}

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Song currSong=null;
        try {
            currSong=songService.updateSong(id, song);
            return ResponseEntity.ok(currSong);
        } catch (SongException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }


}
