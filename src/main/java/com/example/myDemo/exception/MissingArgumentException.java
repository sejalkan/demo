package com.example.myDemo.exception;

public class MissingArgumentException extends RuntimeException {
    public MissingArgumentException(String message) {
        super(message);
    }
}