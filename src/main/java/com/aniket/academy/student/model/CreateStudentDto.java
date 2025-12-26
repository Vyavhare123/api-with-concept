package com.aniket.academy.student.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateStudentDto {
    private String name;
    private String email;
    private String age;
}
