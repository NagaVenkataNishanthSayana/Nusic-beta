package com.example.Nusic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists",uniqueConstraints =@UniqueConstraint(name ="UniqueUserPlayList" ,columnNames = {"playlist_name","user_id"}))
public class PlayList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playlist_name",nullable = false,length = 25)
    private String playListName;

    //Mapping one-many
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    //Mapping one-many
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
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
