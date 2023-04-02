package com.example.Nusic.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String albumName;
    private int yearOfRelease;
    private String leadArtist;

    //mappings one-many
    @OneToMany(mappedBy = "album")
    private Set<Song> songNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Song> getSongNames() {
        return songNames;
    }

    public void setSongNames(Set<Song> songNames) {
        this.songNames = songNames;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getLeadArtist() {
        return leadArtist;
    }

    public void setLeadArtist(String leadArtist) {
        this.leadArtist = leadArtist;
    }
}
