package com.example.Nusic.controller;


import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.exception.SongException;
import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.AlbumService;
import com.example.Nusic.service.SongService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id)  {
        Album album= null;
        try {
            album = albumService.getAlbumById(id);
            return ResponseEntity.ok(album);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums= null;
        try {
            albums = albumService.getAllAlbums();
            return ResponseEntity.ok(albums);
        } catch (AlbumException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping
    public ResponseEntity<Album> getAlbumByName(@RequestParam(value = "albumName") String albumName) {
        Album currAlbum=null;
        try {
            currAlbum= albumService.getAlbumByName(albumName);

            return ResponseEntity.ok(currAlbum);
        } catch (AlbumException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @PostMapping("/")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album)  {
        Album currAlbum=null;
        try {
            currAlbum=albumService.createAlbum(album);
            return ResponseEntity.ok(currAlbum);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{albumId}/songs")
    public ResponseEntity<Song> addSongToAlbum(@PathVariable Long albumId , @RequestBody Song song) {
        Song currSong=null;
        try {
            currSong= songService.addSongToAlbum(albumId,song);
            return new ResponseEntity(currSong, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Album newAlbum=null;
        try{
            newAlbum=albumService.updateAlbum(id, album);
            return new ResponseEntity(newAlbum, HttpStatus.OK);
        } catch (AlbumException e) {
            e.printStackTrace();
            return new ResponseEntity(newAlbum, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/songs/{songId}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Song currSong=null;

        try {
            currSong=songService.updateSong(id, song);
            return new ResponseEntity<>(currSong,HttpStatus.OK);
        } catch (SongException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/songs/{songId}")
    public ResponseEntity deleteSongFromAlbum(@PathVariable Long albumId , @PathVariable Long songId) {
        try {
            albumService.deleteSongFromAlbum(albumId,songId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (AlbumException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAlbum(@PathVariable Long id){
        try {
            albumService.deleteAlbum(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (AlbumException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}