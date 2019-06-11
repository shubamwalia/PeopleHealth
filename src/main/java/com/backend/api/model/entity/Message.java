package com.backend.api.model.entity;

import com.backend.api.model.sro.MessageSro;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String dateAndTime;

    public Message() {
    }

    public Message(MessageSro messageSro) {
        this.role = messageSro.getRole();
        this.message = messageSro.getMessage();
        this.dateAndTime = messageSro.getDateAndTime();
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
