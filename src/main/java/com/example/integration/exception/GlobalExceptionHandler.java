package com.example.integration.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 401 Unauthorized
    @ExceptionHandler({
            UnauthorizedException.class,
            HttpClientErrorException.Unauthorized.class
    })
    public ResponseEntity<ApiErrorResponse> handleUnauthorized(
            Exception ex,
            HttpServletRequest request) {

        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    // 500 Internal Server Error (3rd-party API failures, parsing, etc.)
    @ExceptionHandler({
            IntegrationException.class,
            HttpServerErrorException.class,
            Exception.class
    })
    public ResponseEntity<ApiErrorResponse> handleInternalServerError(
            Exception ex,
            HttpServletRequest request) {

        ApiErrorResponse error = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred while processing the request",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

