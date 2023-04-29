package com.example.Nusic.controller;


import com.example.Nusic.exception.*;
import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.AlbumService;
import com.example.Nusic.service.SongService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE },allowCredentials = "true",allowedHeaders = "SESSION_ID")
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
        }catch ( DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | DatabaseException e) {
            throw e;
        }catch (AlbumException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums= null;
        try {
            albums = albumService.getAllAlbums();
            return ResponseEntity.ok(albums);
        }catch ( DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | DatabaseException | LengthException e) {
            throw e;
        }catch (AlbumException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }

    }

    @GetMapping
    public ResponseEntity<List<Album>> getAlbumsByName(@RequestParam(value = "albumName") String albumName) {
        List<Album> albums=null;
        try {
            albums= albumService.getAlbumByName(albumName);

            return ResponseEntity.ok(albums);
        }catch ( DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | DatabaseException | LengthException e) {
            throw e;
        }catch (AlbumException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }

    }


    @PostMapping("/")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album)  {
        Album currAlbum=null;
        try {
            currAlbum=albumService.createAlbum(album);
            return ResponseEntity.ok(currAlbum);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException |DatabaseException | PasswordMismatchException e){
            throw e;
        }catch (AlbumException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }
    }

    @PostMapping("/{albumId}/songs")
    public ResponseEntity<Song> addSongToAlbum(@PathVariable Long albumId , @RequestBody Song song) {
        Song currSong=null;
        try {
            currSong= songService.addSongToAlbum(albumId,song);
            return new ResponseEntity(currSong, HttpStatus.OK);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException |DatabaseException | PasswordMismatchException e){
            throw e;
        } catch (SongException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Album newAlbum=null;
        try{
            newAlbum=albumService.updateAlbum(id, album);
            return new ResponseEntity(newAlbum, HttpStatus.OK);
        }catch ( DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | DatabaseException e) {
            throw e;
        }catch (AlbumException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }
    }

    @PutMapping("/{id}/songs/{songId}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Song currSong=null;

        try {
            currSong=songService.updateSong(id, song);
            return new ResponseEntity<>(currSong,HttpStatus.OK);
        }catch ( DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | DatabaseException e) {
            throw e;
        }catch (SongException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{albumId}/songs/{songId}")
    public ResponseEntity deleteSongFromAlbum(@PathVariable Long albumId , @PathVariable Long songId) {
        try {
            albumService.deleteSongFromAlbum(albumId,songId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch ( DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | DatabaseException e) {
            throw e;
        }catch (AlbumException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAlbum(@PathVariable Long id){
        try {
            albumService.deleteAlbum(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch ( DatabaseConnectionException | OptimisticLockException | EntityNotFoundException | UnknownSqlException | DatabaseException e) {
            throw e;
        }catch (AlbumException e) {
            e.printStackTrace();
            throw new RuntimeException("Internal Server Error",e);
        }
    }
}