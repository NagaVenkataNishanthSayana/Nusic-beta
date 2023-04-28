package com.example.Nusic.controller;

import com.example.Nusic.exception.*;
import com.example.Nusic.model.PlayList;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.PlayListService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE },allowCredentials = "true")
@RequestMapping("/users")
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    @GetMapping("{userId}/playlists/{id}")
    public ResponseEntity<PlayList> getPlaylistById(@PathVariable Long id) {
        PlayList playList=null;
        try {
            playList=playListService.getPlaylistById(id);
            return ResponseEntity.ok(playList);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException |
                OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch (PlayListException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("{userId}/playlists")
    public ResponseEntity<List<PlayList>> getAllPlaylists(@PathVariable Long userId) {
        List<PlayList> playLists=null;
        try {
            playLists= playListService.getAllPlaylists(userId);
            return ResponseEntity.ok(playLists);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch (PlayListException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @GetMapping("{userId}/playlist")
    public ResponseEntity<PlayList> getPlayListByName(@RequestParam String playlistName)  {
        PlayList playList=null;
        try {
            playList=playListService.getPlayListByName(playlistName);
            return ResponseEntity.ok(playList);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch (PlayListException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("{userId}/playlists/{id}/songs")
    public ResponseEntity<PlayList> addSongToPlayList(@PathVariable Long id, @RequestBody Song song){
        PlayList playList=null;
        try {
            playList=playListService.addSongToPlayList(song, id);
            return ResponseEntity.ok(playList);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch (PlayListException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("{userId}/playlists/{id}")
    public ResponseEntity<PlayList> updatePlaylist(@PathVariable Long id, @RequestBody PlayList playlist) {
        try {
            PlayList currPlayList=null;
             currPlayList=playListService.updatePlaylist(id, playlist);
             return ResponseEntity.ok(currPlayList);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch (PlayListException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{userId}/playlists/{id}/songs/{songsId}")
    public ResponseEntity<PlayList> removeSongFromPlayList(@PathVariable Long id, @PathVariable Long songsId){
        PlayList currPlayList=null;
        try {
            currPlayList=playListService.removeSongFromPlayList(id,songsId);
            return ResponseEntity.ok(currPlayList);
        }catch(DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch(PlayListException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{userId}/playlists/{id}")
    public void deletePlaylist(@PathVariable Long id){
        try{
            playListService.deletePlaylist(id);
        }catch(DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch(PlayListException e) {
            throw new RuntimeException(e);
        }
    }
}