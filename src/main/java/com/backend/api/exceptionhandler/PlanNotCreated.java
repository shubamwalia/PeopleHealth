package com.backend.api.exceptionhandler;

public class PlanNotCreated extends RuntimeException {
    public PlanNotCreated(String message) {
        super(message);
    }
}
