package com.aniket.academy.student.controller;

import com.aniket.academy.student.model.CreateStudentDto;
import com.aniket.academy.student.model.StudentDto;
import com.aniket.academy.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Student APIs", description = "CRUD operations on students")
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @Operation(
            summary = "Create Student",
            description = "Creates a new student record"
    )
    @PostMapping
    public ResponseEntity<StudentDto>SaveStudent(@RequestBody CreateStudentDto studentDto){
        StudentDto saveStudents=studentService.saveStudent(studentDto);
        return new ResponseEntity<StudentDto>(saveStudents, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>>getAllStudents(){
        List<StudentDto> Students=studentService.getAllStudents();
        return new ResponseEntity<List<StudentDto>>(Students, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto>getStudentById(@PathVariable Long id){
        StudentDto saveStudents=studentService.getStudentById(id);
        return new ResponseEntity<StudentDto>(saveStudents, HttpStatus.OK);
    }
    @PutMapping(("/{id}"))
    public ResponseEntity<StudentDto>updateStudent(@RequestBody StudentDto studentDto,@PathVariable Long id){
        StudentDto saveStudents=studentService.updateStudent(studentDto,id);
        return new ResponseEntity<StudentDto>(saveStudents, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
