package com.generation.api.generation_brasil_challenge.exceptions.messages;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorMessage extends ApiErrorMessage {

    private List<FieldErrorMessage> errors = new ArrayList<>();

    public void addError(FieldErrorMessage message){
        errors.add(message);
    }
}
