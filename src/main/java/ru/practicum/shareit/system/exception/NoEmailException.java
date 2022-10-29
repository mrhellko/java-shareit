package ru.practicum.shareit.system.exception;

public class NoEmailException extends RuntimeException {
    public NoEmailException(String message) {
        super(message);
    }
}
