package com.chernovol.springbootproject.exception;

public class ConflictException extends RuntimeException {
    private String name;

    public ConflictException(String message, String name) {
        super(message);
        this.name = name;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + name;
    }
}
