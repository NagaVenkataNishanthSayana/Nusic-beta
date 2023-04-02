package com.example.Nusic.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    long id;

    private String albumName;
    private int yearOfRelease;
    private String leadArtist;

    //mappings one-many
    @OneToMany(mappedBy = "song")
    private Set<Song> songNames;



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
