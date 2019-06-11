package com.backend.api.exceptionhandler;

public class InvalidPhotoException extends Exception {

    public InvalidPhotoException(String message) {
        super(message);
    }

    public String getField() {
        return "photo";
    }
}
