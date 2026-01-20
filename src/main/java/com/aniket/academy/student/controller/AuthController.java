package com.aniket.academy.student.controller;

import com.aniket.academy.student.dto.LoginRequestDto;
import com.aniket.academy.student.dto.LoginResponseDto;
import com.aniket.academy.student.dto.SignUpRequestDto;
import com.aniket.academy.student.dto.SignupResponseDto;
import com.aniket.academy.student.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
   @PostMapping("/login")
    public ResponseEntity<LoginResponseDto>login(@RequestBody LoginRequestDto loginRequestDto){
        LoginResponseDto loginResponseDto=authService.login(loginRequestDto);
        return new ResponseEntity<>(loginResponseDto,HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }

}
