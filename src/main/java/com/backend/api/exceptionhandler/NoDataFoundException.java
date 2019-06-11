package com.backend.api.exceptionhandler;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException(){
        super("no data found");
    }

    public NoDataFoundException(String message) {
        super(message);
    }
}
