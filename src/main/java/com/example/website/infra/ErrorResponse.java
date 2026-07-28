package com.example.website.infra;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Getter
@NoArgsConstructor
@JsonPropertyOrder({"timestamp", "status", "error", "fields"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Instant timestamp;
    private Integer status;
    private String error;
    private Map<String, String> fields;

    public ErrorResponse(Integer status, String error) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
    }

    public ErrorResponse(Integer status, String error, Map<String, String> fields) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.fields = fields;
    }
}
