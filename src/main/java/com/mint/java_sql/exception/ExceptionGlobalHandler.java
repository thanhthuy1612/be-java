package com.mint.java_sql.exception;

import com.mint.java_sql.config.Translator;
import com.mint.java_sql.dto.response.ResponseData;
import com.mint.java_sql.dto.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionGlobalHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError accountExistedHandler(ResourceNotFoundException ex) {
        return new ResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(ResourceForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError accountExistedHandler(ResourceForbiddenException ex) {
        return new ResponseError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), Translator.toLocale("bad.request"), errors);
    }
}
