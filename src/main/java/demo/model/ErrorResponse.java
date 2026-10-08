package demo.model;

public record ErrorResponse(
        String message,
        int status,
        String timestamp
){ }
