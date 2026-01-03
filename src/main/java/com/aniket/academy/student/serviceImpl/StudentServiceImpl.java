package com.aniket.academy.student.serviceImpl;

import com.aniket.academy.student.Entiity.Student;
import com.aniket.academy.student.exception.StudentNotFoundException;
import com.aniket.academy.student.model.CreateStudentDto;
import com.aniket.academy.student.model.StudentDto;
import com.aniket.academy.student.repository.StudentRepository;
import com.aniket.academy.student.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    // we have used RequiredArgsConstructor so we did not required to create constructor for dependency injection
    // Here we are using constructor injection instead of field injection
    final private StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Override
    @CacheEvict(value = "students", allEntries = true)
    public StudentDto saveStudent(CreateStudentDto studentDto) {
        log.info("inside saveStudent", studentDto);
      Student student= objectMapper.convertValue(studentDto, Student.class);
        Student saveStudent=studentRepository.save(student);
        return objectMapper.convertValue(saveStudent, StudentDto.class);
    }

    @Override
    @Cacheable(value = "students")
    public List<StudentDto> getAllStudents() {
        log.info("inside getAllStudents");
        List<StudentDto> listOfStudent=studentRepository.findAll().stream().map(Student->objectMapper.convertValue(Student,StudentDto.class)).collect(Collectors.toList());
        return listOfStudent;
    }

    @Override
    @Cacheable(value = "studentById", key = "#id")
    public StudentDto getStudentById(Long id) {
        log.info("Inside getStudentById" , id);
      Student student= studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        return objectMapper.convertValue(student,StudentDto.class);
    }

    @Override
    @CachePut(value = "studentById", key = "#id")
    @CacheEvict(value = "students", allEntries = true)
    public StudentDto updateStudent(StudentDto studentDto, Long id) {
        log.info("student updated with", id);
        Student student= studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        student.setAge(studentDto.getAge());
        student.setId(studentDto.getId());
        student.setEmail(studentDto.getEmail());
        student.setName(studentDto.getName());
        studentRepository.save(student);
        return objectMapper.convertValue(student,StudentDto.class);
    }

    @Override
    @CacheEvict(value = { "studentById", "students" }, allEntries = true)
    public void deleteStudent(Long id) {
        log.info("deleted Student with id", id);
        Student student= studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        studentRepository.deleteById(id);
    }
}
