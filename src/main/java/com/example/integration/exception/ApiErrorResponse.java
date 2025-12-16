package com.example.integration.exception;


import java.time.Instant;

public class ApiErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;
    private Instant timestamp = Instant.now();

    public ApiErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public Instant getTimestamp() { return timestamp; }
}

