package com.example.Nusic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "songs",uniqueConstraints={@UniqueConstraint(columnNames = {"song_path","song_name","album_id"})})
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "song_path",nullable = false,unique = true,length = 50)
    private String songPath;

    @Column(name = "song_name",nullable = false, length = 50)
    private String songName;

    @Column(nullable = false)
    private String artists;

    @JsonIgnore
    @ManyToMany(mappedBy = "songs",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PlayList> playlists;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id",nullable = false)
    private Album album;

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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

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
