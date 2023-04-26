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
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/playlists")
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayList> getPlaylistById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        PlayList playList=null;
        try {
            playList=playListService.getPlaylistById(id);
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            Cookie sessionCookie = new Cookie("SESSION_ID", sessionId);
            sessionCookie.setPath("/");
            response.addCookie(sessionCookie);
            return ResponseEntity.ok(playList);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException |
                OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch (PlayListException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayList>> getAllPlaylists() {
        List<PlayList> playLists=null;
        try {
            playLists= playListService.getAllPlaylists();
            return ResponseEntity.ok(playLists);
        }catch (DuplicateEntryException | ForeignKeyConstraintException | DatabaseConnectionException | OptimisticLockException |
                EntityNotFoundException | UnknownSqlException | PasswordMismatchException e) {
            throw e;
        }catch (PlayListException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @GetMapping
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

    @PostMapping("/{id}/songs")
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

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}/songs/{songsId}")
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

    @DeleteMapping("/{id}")
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