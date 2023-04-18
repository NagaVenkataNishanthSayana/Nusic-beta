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
    public Album updateAlbum(Long id, Album album) throws AlbumException {
        return albumDAO.updateAlbum(id,album);
    }

    @Override
    public void deleteAlbum(Long id) throws AlbumException {
        albumDAO.deleteAlbum(id);
    }

    @Override
    public List<Album> getAllAlbums() throws AlbumException {
        return albumDAO.getAllAlbums();
    }

    @Override
    public Album getAlbumByName(String albumName) throws AlbumException {
        return albumDAO.getAlbumByName(albumName);
    }

    @Override
    public void deleteSongFromAlbum(Long albumId, Long songId) throws AlbumException {
         albumDAO.deleteSongFromAlbum(albumId,songId);
    }
}
