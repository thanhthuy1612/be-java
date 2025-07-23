package com.mint.java_sql.exception;

import com.mint.java_sql.dto.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
