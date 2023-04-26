package com.example.Nusic.service;

import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.model.Album;

import java.util.List;

public interface AlbumService {
    Album getAlbumById(Long id) throws AlbumException;

    Album createAlbum(Album album) throws AlbumException;

    Album updateAlbum(Long id, Album album) throws AlbumException;

    void deleteAlbum(Long id) throws AlbumException;

    List<Album> getAllAlbums() throws AlbumException;

    List<Album> getAlbumByName(String albumName) throws AlbumException;

    void deleteSongFromAlbum(Long albumId, Long songId) throws AlbumException;
}
