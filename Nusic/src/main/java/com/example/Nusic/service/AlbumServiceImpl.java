package com.example.Nusic.service;

import com.example.Nusic.DAO.AlbumDAO;
import com.example.Nusic.exception.AlbumException;
import com.example.Nusic.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDAO albumDAO;

    @Override
    public Album getAlbumById(Long id) throws AlbumException {
        return albumDAO.getByAlbumId(id);
    }

    @Override
    public Album createAlbum(Album album) throws AlbumException {
        return albumDAO.create(album);
    }

    @Override
    public Album updateAlbum(Long id, Album album) {
        return null;
    }

    @Override
    public void deleteAlbum(Long id) {

    }

    @Override
    public List<Album> getAllAlbums() throws AlbumException {
        return albumDAO.getAllAlbums();
    }
}
