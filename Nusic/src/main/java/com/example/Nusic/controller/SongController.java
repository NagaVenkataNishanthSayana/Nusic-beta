package com.example.Nusic.controller;


import com.example.Nusic.exception.*;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.SongService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE },allowCredentials = "true")
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Song song=null;
        try {
            song=songService.getSongById(id);
            return ResponseEntity.ok(song);
        }catch (ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException e) {
            throw e;
        }catch (SongException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<Song>> getSongByName(@RequestParam(value = "songName") String songName){
        List<Song> songs=null;
        try {
            songs=songService.getSongsByName(songName);
            return ResponseEntity.ok(songs);
        }catch (ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | LengthException e) {
            throw e;
        }catch (SongException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Song>> getAllSongs() {

        List<Song> songs=null;
        try {
            songs=songService.getAllSongs();
            return ResponseEntity.ok(songs);
        }catch (ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | LengthException e) {
            throw e;
        }catch (SongException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Song currSong=null;
        try {
            currSong=songService.updateSong(id, song);
            return ResponseEntity.ok(currSong);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException e) {
            throw e;
        } catch (SongException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
