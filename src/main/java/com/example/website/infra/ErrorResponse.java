package com.example.website.infra;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

@Getter
@NoArgsConstructor
@JsonPropertyOrder({"timestamp", "status", "title", "message", "fields"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Instant timestamp;
    private Integer status;
    private String title;
    private String message;
    private Map<String, String> fields;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.timestamp = Instant.now();
        this.status = httpStatus.value();
        this.title = httpStatus.getReasonPhrase();
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message, Map<String, String> fields) {
        this(status, message);
        this.fields = fields;
    }
}
