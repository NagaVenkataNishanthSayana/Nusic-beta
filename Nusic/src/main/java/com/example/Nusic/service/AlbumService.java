package com.example.Nusic.service;

import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.model.Album;

import java.util.List;

public interface AlbumService {
    Album getAlbumById(Long id) throws Exception;

    Album createAlbum(Album album) throws Exception;

    Album updateAlbum(Long id, Album album) throws AlbumException;

    void deleteAlbum(Long id) throws AlbumException;

    List<Album> getAllAlbums() throws AlbumException;

    Album getAlbumByName(String albumName) throws AlbumException;

    void deleteSongFromAlbum(Long albumId, Long songId) throws AlbumException;
}
