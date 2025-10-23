package org.example;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException() {
        super("The specified key was not found in the map.");
    }

    public KeyNotFoundException(String message) {
        super(message);
    }
}
