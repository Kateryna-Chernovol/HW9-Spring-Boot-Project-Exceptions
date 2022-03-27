package com.chernovol.springbootproject.exception;

public class DepartureNotFoundException extends RuntimeException {
    private String name;

    public DepartureNotFoundException(String message, String name) {
        super(message);
        this.name = name;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + name;
    }
}
