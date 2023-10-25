package com.generation.api.generation_brasil_challenge.exceptions.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorMessage {

    private Integer status;
    private Instant timestamp;
    private String path;
    private String message;
    private String error;
}
