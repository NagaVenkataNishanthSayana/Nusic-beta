package com.example.Nusic.service;

import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.Album;

import java.util.List;

public interface AlbumService {
    Album getAlbumById(Long id) throws Exception;

    Album createAlbum(Album album) throws Exception;

    Album updateAlbum(Long id, Album album);

    void deleteAlbum(Long id);

    List<Album> getAllAlbums() throws AlbumException;
}
