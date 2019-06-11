package com.backend.api.model.sro;

import com.backend.api.model.entity.User;

public class UserSro {

    private int id;
    private String email;
    private Boolean enable;

    public UserSro() {
    }

    public UserSro(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.enable = user.getEnable();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
