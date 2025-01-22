package com.arun.health.advice;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String error;
    private HttpStatusCode httpStatusCode;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatusCode httpStatusCode) {
        this();
        this.error = error;
        this.httpStatusCode = httpStatusCode;
    }
}
