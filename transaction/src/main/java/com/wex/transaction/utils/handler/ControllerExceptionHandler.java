package com.wex.transaction.utils.handler;

import com.wex.transaction.application.dto.response.ErrorMessageResponse;
import com.wex.transaction.infrastructure.exception.ExchangeRateFetchException;
import com.wex.transaction.infrastructure.exception.TransactionNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String MESSAGE_ERROR_DEFAULT = "Não foi possível processar sua requisição!";

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> accountNotFoundException(TransactionNotFoundException exception) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now(),
                exception.getMessage(),
                exception.getDescription());

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(ExchangeRateFetchException.class)
    public ResponseEntity<ErrorMessageResponse> exchangeRateFetchException(ExchangeRateFetchException exception) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now(),
                exception.getMessage(),
                exception.getDescription());

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach(error -> {
            String fieldName = error.getObjectName().toString();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException exception) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now(),
                exception.getMessage(),
                MESSAGE_ERROR_DEFAULT);

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            String fieldName = error.getPropertyPath().toString();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
