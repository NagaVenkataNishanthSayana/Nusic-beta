package com.example.Nusic.service;

import com.example.Nusic.model.Album;

public interface AlbumService {
    Album getAlbumById(Long id);

    Album createAlbum(Album album);

    Album updateAlbum(Long id, Album album);

    void deleteAlbum(Long id);
}
