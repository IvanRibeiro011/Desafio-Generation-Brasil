package com.generation.api.generation_brasil_challenge.exceptions.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FieldErrorMessage {

    private String fieldName;
    private String error;
}
