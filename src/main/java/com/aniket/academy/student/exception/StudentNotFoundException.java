package com.aniket.academy.student.exception;

public class StudentNotFoundException extends  RuntimeException {
    public StudentNotFoundException(Long id){
        super("student not found with given id "+ id );
    }
}
