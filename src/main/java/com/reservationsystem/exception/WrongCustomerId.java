package com.reservationsystem.exception;

public class WrongCustomerId extends RuntimeException {
    public WrongCustomerId(String message) {
        super(message);
    }
}
