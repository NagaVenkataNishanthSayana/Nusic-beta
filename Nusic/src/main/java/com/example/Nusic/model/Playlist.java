package com.example.Nusic.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Playlist {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playListName;

    //Mapping one-many
    @ManyToOne
    private User user;

    //Mapping one-many
    @ManyToMany(mappedBy = "playlists")
    List<Song> playListSongs;

    public List<Song> getPlayListSongs() {
        return playListSongs;
    }

    public void setPlayListSongs(List<Song> playListSongs) {
        this.playListSongs = playListSongs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

}
