package com.example.Nusic.service;

import com.example.Nusic.DAO.SongDAO;
import com.example.Nusic.exception.LengthException;
import com.example.Nusic.exception.SongException;
import com.example.Nusic.model.Song;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongDAO songDAO;

    @Override
    public Song getSongById(Long id) throws SongException {
        Song song= songDAO.getSongById(id);
        if(song==null) throw new EntityNotFoundException("Song with this ID not Found");
        return song;
    }

    @Override
    public Song addSongToAlbum(Long albumId, Song song) throws SongException {

        return songDAO.addSongToAlbum(albumId,song);
    }

    @Override
    public Song updateSong(Long id, Song song) throws SongException {
        return songDAO.updateSong(id,song);
    }


    @Override
    public List<Song> getSongByPlayListId(Long id) {
        return null;
    }

    @Override
    public List<Song> getAllSongs() throws SongException {
        return songDAO.getAllSongs();
    }

    @Override
    public List<Song> getSongByAlbumId(Long id) {
        return null;
    }

    @Override
    public List<Song> getSongsByName(String songName) throws SongException {
        if(songName.length()<3) throw new LengthException("Length of the Song Name should be at least 3");
        List<Song> songs= songDAO.getSongsByName(songName);
        if(songs==null || (songs!=null && songs.size()==0)) throw new EntityNotFoundException("No Song Found with this Name");
        return songs;
    }
}
