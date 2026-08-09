package com.aniket.academy.student.exception;

import com.aniket.academy.student.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerForApi {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException ex) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UsernameNotFoundException ex) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(err, HttpStatus.NOT_FOUND);
    }
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex){
        log.error("Unhandled exception occurred", ex);
        ErrorResponse err = new ErrorResponse();
        err.setMessage("something Went Wrong With request123");
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(err,HttpStatus.BAD_REQUEST);
    }*/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse>handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex){
        ErrorResponse err = new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage("please enter correct end point or Api url");
        err.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity<ErrorResponse>(err,HttpStatus.METHOD_NOT_ALLOWED);
    }
}