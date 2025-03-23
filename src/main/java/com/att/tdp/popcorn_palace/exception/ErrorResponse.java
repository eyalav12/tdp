package com.att.tdp.popcorn_palace.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, int status,LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }
}
