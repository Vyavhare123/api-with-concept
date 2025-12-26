package com.aniket.academy.student.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentDto {
    private Long id ;
    private String name;
    private String email;
    private String age;
}
