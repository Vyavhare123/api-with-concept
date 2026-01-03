package com.aniket.academy.student.model;

import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateStudentDto {
    private String name;
    private String email;
    private String age;
}
