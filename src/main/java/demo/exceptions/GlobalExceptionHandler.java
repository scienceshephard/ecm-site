package demo.exceptions;


import demo.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExternalApiUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleExternalApiUnavailable(
            ExternalApiUnavailableException e) {

        ErrorResponse error = new ErrorResponse(
                "Image service is currently unavailable",
                503,
                Instant.now().toString()
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(error);
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ErrorResponse> handleExternalApi(
            ExternalApiException e) {

        ErrorResponse error = new ErrorResponse(
                e.getMessage(),
                502,
                Instant.now().toString()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            IllegalArgumentException e) {

        ErrorResponse error = new ErrorResponse(
                e.getMessage(),
                400,
                Instant.now().toString()
        );

        return ResponseEntity
                .badRequest()
                .body(error);
    }
}