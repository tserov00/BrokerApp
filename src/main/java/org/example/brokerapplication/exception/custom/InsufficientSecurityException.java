package org.example.brokerapplication.exception.custom;

public class InsufficientSecurityException extends RuntimeException {
    public InsufficientSecurityException(String message) {
        super(message);
    }
}
