package com.aniket.academy.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StudentCrudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCrudServiceApplication.class, args);
	}

}
