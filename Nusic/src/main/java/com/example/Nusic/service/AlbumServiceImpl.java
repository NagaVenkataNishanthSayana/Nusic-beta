package com.example.Nusic.service;

import com.example.Nusic.DAO.AlbumDAO;
import com.example.Nusic.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDAO albumDAO;

    @Override
    public Album getAlbumById(Long id) throws Exception {
        return albumDAO.getByAlbumId(id);
    }

    @Override
    public Album createAlbum(Album album) throws Exception {
        return albumDAO.create(album);
    }

    @Override
    public Album updateAlbum(Long id, Album album) {
        return null;
    }

    @Override
    public void deleteAlbum(Long id) {

    }
}
