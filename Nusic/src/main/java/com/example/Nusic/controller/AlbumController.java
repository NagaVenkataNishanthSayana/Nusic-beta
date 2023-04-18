package com.example.Nusic.controller;


import com.example.Nusic.exception.AlbumException;
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
    public Album getAlbumById(@PathVariable Long id) throws Exception {
        Album album= albumService.getAlbumById(id);

        return album;
    }

    @GetMapping("/")
    public List<Album> getAllAlbums() throws AlbumException {
        List<Album> albums= albumService.getAllAlbums();

        return albums;
    }

    @GetMapping
    public Album getAlbumByName(@RequestParam(value = "albumName") String albumName) throws AlbumException {

        return albumService.getAlbumByName(albumName);

    }


    @PostMapping("/")
    public Album createAlbum(@RequestBody Album album) throws Exception {
        return albumService.createAlbum(album);
    }

    @PostMapping("/{albumId}/songs")
    public Song addSongToAlbum(@PathVariable Long albumId , @RequestBody Song song) throws Exception {
        return songService.addSongToAlbum(albumId,song);
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
    public Song updateSong(@PathVariable Long id, @RequestBody Song song) {
        return songService.updateSong(id, song);
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
    public ResponseEntity<?> deleteAlbum(@PathVariable Long id){
        try {
            albumService.deleteAlbum(id);
            return ResponseEntity.status(HttpStatus.GONE).build();
        } catch (AlbumException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}