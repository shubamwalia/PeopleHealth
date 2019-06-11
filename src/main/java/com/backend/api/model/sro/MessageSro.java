package com.backend.api.model.sro;

import com.backend.api.model.entity.Message;
import com.backend.api.helper.custom_validation_anotaions.anotaion.ValidRole;

import javax.validation.constraints.Size;

public class MessageSro {

    private int id;

    @ValidRole
    private String role;

    @Size(min = 2, max = 30, message = "2 <= (message length) <= 30")
    private String message;

    private String dateAndTime;

    public MessageSro() {
    }

    public MessageSro(Message message) {
        this.id = message.getId();
        this.role = message.getRole();
        this.message = message.getMessage();
        this.dateAndTime = message.getDateAndTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

}
