package com.app.exceptions.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ErrorMessage {
    private String error;
    private String message;

    @JsonCreator
    public ErrorMessage(@JsonProperty String error,
                        @JsonProperty String message) {
        this.error = error;
        this.message = message;
    }
}