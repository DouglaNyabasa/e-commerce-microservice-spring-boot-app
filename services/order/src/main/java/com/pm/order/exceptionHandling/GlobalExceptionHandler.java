package com.pm.order.exceptionHandling;
import com.pm.order.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handle(BusinessException exception) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exception.getMsg());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException exception) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exception.getMessage());
    }

 

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String,String>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }



}
