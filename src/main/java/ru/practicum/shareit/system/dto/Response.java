package ru.practicum.shareit.system.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class Response {
    private final String message;
    private final String stackTrace;

    public Response(Exception e) {
        this.message = e.getMessage();
        this.stackTrace = Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
