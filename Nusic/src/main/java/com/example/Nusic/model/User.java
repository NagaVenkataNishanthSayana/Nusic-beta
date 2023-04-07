package com.example.Nusic.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String fistName;

    @Column(name = "last_name",nullable = false)
    private String lastname;

    @Column(name ="password",nullable = false)
    private String password;

    @Column(unique=true,nullable = false,length = 50)
    private String email;

    //Mappings one-many
    @OneToMany(mappedBy = "user")
    private Set<PlayList> playlists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<PlayList> playlists) {
        this.playlists = playlists;
    }

    public User() {
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
