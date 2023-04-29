package com.example.Nusic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(name = "first_name",nullable = false)
    private String firstName;


    @NotNull
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @NotNull
    @Column(name ="password",nullable = false)
    private String password;

    @Column(nullable = false , columnDefinition = "VARCHAR(20) DEFAULT 'user'")
    private String role;

    @NotNull
    @Email
    @Column(unique=true,nullable = false,length = 50)
    private String email;

    //Mappings one-many
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<PlayList> playlists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<PlayList> playlists) {
        this.playlists = playlists;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
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
