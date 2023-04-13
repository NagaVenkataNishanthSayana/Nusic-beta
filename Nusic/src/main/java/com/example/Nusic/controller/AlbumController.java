package com.example.Nusic.controller;


import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.model.Album;
import com.example.Nusic.model.Song;
import com.example.Nusic.service.AlbumService;
import com.example.Nusic.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/")
    public Album createAlbum(@RequestBody Album album) throws Exception {
        return albumService.createAlbum(album);
    }

    @PostMapping("/{albumId}/songs")
    public Song addSongToAlbum(@PathVariable Long albumId , @RequestBody Song song) throws Exception {
        return songService.addSong(albumId,song);
    }

    @PutMapping("/{id}")
    public Album updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        return albumService.updateAlbum(id, album);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }
}