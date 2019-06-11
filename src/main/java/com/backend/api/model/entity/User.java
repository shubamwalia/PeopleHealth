package com.backend.api.model.entity;

import com.backend.api.helper.validation_objects.Signup;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Boolean enable;

    public User() {
    }

    public User(String email, String password, String role, Boolean enable) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.enable = enable;
    }

    public User(Signup signup, Boolean enable) {
        this.email = signup.getEmail();
        this.password = signup.getPassword();
        this.role = signup.getRole();
        this.enable = enable;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
