package com.aniket.academy.student.exception;

import com.aniket.academy.student.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException ex) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex){
        log.error("Unhandled exception occurred", ex);
        ErrorResponse err = new ErrorResponse();
        err.setMessage("something Went Wrong With request");
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(err,HttpStatus.BAD_REQUEST);
    }
}