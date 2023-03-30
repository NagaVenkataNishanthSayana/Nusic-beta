package com.example.Nusic.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Playlist {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String playListName;

    //Mapping one-one
    private long userId;


    //Mapping one-many
    Set<Integer> playListSongs;

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
