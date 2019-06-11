package com.backend.api.helper.response;

import com.backend.api.helper.validation_objects.Signup;

public class BasicDataResponse {

    private int id;
    private String name;
    private String email;
    private String city;
    private Boolean enabled;

    public BasicDataResponse() {
    }

    public BasicDataResponse(Signup signup) {
        this.id = signup.getId();
        this.name = signup.getName();
        this.email = signup.getEmail();
        this.city = signup.getCity();
    }

    public BasicDataResponse(Signup signup, Boolean enabled) {
        this.id = signup.getId();
        this.name = signup.getName();
        this.email = signup.getEmail();
        this.city = signup.getCity();
        this.enabled = enabled;
    }

    public BasicDataResponse(int id, String name, String email, String city, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
