package com.aniket.academy.student.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//added Serializable for to stored data in redis
public class StudentDto implements Serializable{
    private Long id ;
    private String name;
    private String email;
    private String age;
}
