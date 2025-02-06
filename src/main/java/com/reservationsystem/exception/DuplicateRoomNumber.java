package com.reservationsystem.exception;

public class DuplicateRoomNumber extends RuntimeException {
    public DuplicateRoomNumber(String message) {
        super(message);
    }
}
