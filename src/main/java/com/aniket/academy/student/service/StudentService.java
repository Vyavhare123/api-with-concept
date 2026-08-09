package com.aniket.academy.student.service;

import com.aniket.academy.student.dto.CreateStudentDto;
import com.aniket.academy.student.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(CreateStudentDto studentDto);
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto updateStudent(StudentDto studentDto,Long id);
    void deleteStudent(Long id);
}
