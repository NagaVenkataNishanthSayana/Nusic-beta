package com.example.Nusic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String fistName;
    private String lastname;
    private String password;
    private String email;

    //Mappings one-many
    private Set<Integer> playlists;

    public Set<Integer> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Integer> playlists) {
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
