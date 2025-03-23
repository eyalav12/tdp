package com.att.tdp.popcorn_palace.exception;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolation;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder("Validation failed: ");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            message.append(error.getDefaultMessage()).append("; ");
        }
        for (ObjectError globalError : ex.getBindingResult().getGlobalErrors()) {
            message.append(globalError.getDefaultMessage()).append("; ");
        }

        ErrorResponse errorResponse = new ErrorResponse(
                message.toString().trim(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //handle resource not found excptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //handle request body error throwing
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Required request body is missing or malformed",
                HttpStatus.BAD_REQUEST.value()
                ,LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //handle custom error i made -> sql constraint of not overlapping showtimes in same theater
    @ExceptionHandler(ShowtimeOverlappingException.class)
    public ResponseEntity<ErrorResponse> handleShowtimesOverlapException(ShowtimeOverlappingException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //handle error of sql exception -> when try to update to the db with not valid values
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //handle custom error i made -> for not allowing db of bookings in same theater have same seat number
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleDupalicateKeyValueViolationException(SQLException ex) {
        String errorMessage = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}