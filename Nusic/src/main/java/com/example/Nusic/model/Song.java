package com.example.Nusic.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "songs")
public class Song {


    @Column(name = "song_path",nullable = false)
    private String songPath;

    @Column(name = "song_name",nullable = false)
    private String songName;

    @Column(nullable = false)
    private String artists;

    @ManyToMany(mappedBy = "songs")
    private List<PlayList> playlists;

    public List<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlayList> playlists) {
        this.playlists = playlists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "album_id",nullable = false)
    private Album album;
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }




}
