package com.aniket.academy.student.dto;

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
