package com.aniket.academy.student.service;

import com.aniket.academy.student.dto.LoginRequestDto;
import com.aniket.academy.student.dto.LoginResponseDto;
import com.aniket.academy.student.dto.SignUpRequestDto;
import com.aniket.academy.student.dto.SignupResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    SignupResponseDto signup (SignUpRequestDto signUpRequestDto);
}
