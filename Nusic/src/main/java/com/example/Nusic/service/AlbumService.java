package com.example.Nusic.service;

import com.example.Nusic.exception.UserException;
import com.example.Nusic.model.Album;

public interface AlbumService {
    Album getAlbumById(Long id) throws Exception;

    Album createAlbum(Album album) throws Exception;

    Album updateAlbum(Long id, Album album);

    void deleteAlbum(Long id);
}
