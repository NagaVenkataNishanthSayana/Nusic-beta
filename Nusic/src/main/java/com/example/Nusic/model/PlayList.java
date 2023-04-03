package com.example.Nusic.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "playlists")
public class PlayList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playlist_name",nullable = false)
    private String playListName;

    //Mapping one-many
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    //Mapping one-many
    @ManyToMany
    @JoinTable(name = "playlist_tracks",joinColumns = @JoinColumn(name = "playlist_id",nullable = false),inverseJoinColumns = @JoinColumn(name = "song_id",nullable = false))
    Set<Song> songs;

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
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
