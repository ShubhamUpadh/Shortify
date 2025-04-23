package com.URLShortener.Shortify.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")
public class UserModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    public UserModel() {
    }

    public UserModel(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
