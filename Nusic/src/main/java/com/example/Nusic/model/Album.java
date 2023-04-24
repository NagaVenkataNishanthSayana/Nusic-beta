package com.example.Nusic.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "album_name",nullable = false,unique = true,length = 25)

    private String albumName;

    @Column(name = "year_of_release",nullable = false)
    private int yearOfRelease;

    @Column(name ="lead_artist",nullable = false)
    private String leadArtist;

    //mappings one-many
    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Song> songs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
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
