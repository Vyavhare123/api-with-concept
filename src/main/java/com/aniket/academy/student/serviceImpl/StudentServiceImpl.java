package com.aniket.academy.student.serviceImpl;

import com.aniket.academy.student.Entiity.Student;
import com.aniket.academy.student.exception.StudentNotFoundException;
import com.aniket.academy.student.model.CreateStudentDto;
import com.aniket.academy.student.model.StudentDto;
import com.aniket.academy.student.repository.StudentRepository;
import com.aniket.academy.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    // we have used RequiredArgsConstructor so we did not required to create constructor for dependency injection
    // Here we are using constructor injection instead of field injection
    final private StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Override
    public StudentDto saveStudent(CreateStudentDto studentDto) {
      Student student= objectMapper.convertValue(studentDto, Student.class);
        Student saveStudent=studentRepository.save(student);
        return objectMapper.convertValue(saveStudent, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> listOfStudent=studentRepository.findAll().stream().map(Student->objectMapper.convertValue(Student,StudentDto.class)).collect(Collectors.toList());
        return listOfStudent;
    }

    @Override
    public StudentDto getStudentById(Long id) {
      Student student= studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        return objectMapper.convertValue(student,StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Long id) {
        Student student= studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        student.setAge(studentDto.getAge());
        student.setId(studentDto.getId());
        student.setEmail(studentDto.getEmail());
        student.setName(studentDto.getName());
        studentRepository.save(student);
        return objectMapper.convertValue(student,StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student= studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
        studentRepository.deleteById(id);
    }
}
