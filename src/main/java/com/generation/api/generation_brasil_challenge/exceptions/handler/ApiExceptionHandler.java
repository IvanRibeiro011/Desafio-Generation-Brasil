package com.generation.api.generation_brasil_challenge.exceptions.handler;

import com.generation.api.generation_brasil_challenge.exceptions.ResourceNotFoundException;
import com.generation.api.generation_brasil_challenge.exceptions.messages.ApiErrorMessage;
import com.generation.api.generation_brasil_challenge.exceptions.messages.FieldErrorMessage;
import com.generation.api.generation_brasil_challenge.exceptions.messages.ValidationErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorMessage errorMessage = new ApiErrorMessage();
        errorMessage.setStatus(status.value());
        errorMessage.setTimestamp(Instant.now());
        errorMessage.setPath(request.getRequestURI());
        errorMessage.setMessage("Entity not found");
        errorMessage.setError(e.getMessage());
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorMessage> validationError(MethodArgumentNotValidException e , HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorMessage errorMessage = new ValidationErrorMessage();
        errorMessage.setStatus(status.value());
        errorMessage.setTimestamp(Instant.now());
        errorMessage.setPath(request.getRequestURI());
        errorMessage.setMessage("Validation error");
        errorMessage.setError(e.getMessage());

        for (FieldError fieldError : e.getFieldErrors()){
            errorMessage.addError(new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return ResponseEntity.status(status).body(errorMessage);
    }
}
