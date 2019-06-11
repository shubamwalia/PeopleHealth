package com.backend.api.exceptionhandler;

public class InvalidPaymentException extends Exception {
    public InvalidPaymentException(String s) {
        super(s);
    }
    public String getField() {
        return "payment amount";
    }
}
