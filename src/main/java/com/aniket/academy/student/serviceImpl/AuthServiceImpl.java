package com.aniket.academy.student.serviceImpl;

import com.aniket.academy.student.Entiity.User;
import com.aniket.academy.student.dto.LoginRequestDto;
import com.aniket.academy.student.dto.LoginResponseDto;
import com.aniket.academy.student.dto.SignUpRequestDto;
import com.aniket.academy.student.dto.SignupResponseDto;
import com.aniket.academy.student.repository.UserRepository;
import com.aniket.academy.student.security.AuthUtil;
import com.aniket.academy.student.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtil authUtil;
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
    log.info("inside AuthServiceImpl service class and calling authenticationManager ********************************************************** 4 ");
      Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));
      User user= (User) authentication.getPrincipal();
      String jwtToken =authUtil.generateAccessToken(user);
      LoginResponseDto loginResponseDto=new LoginResponseDto();
        loginResponseDto.setJwt(jwtToken);
        loginResponseDto.setUserId(user.getId());
        return loginResponseDto;
    }

    @Override
    public SignupResponseDto signup(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByUsername(signUpRequestDto.getUsername())) {
            throw new IllegalArgumentException(
                    signUpRequestDto.getUsername() + " already exists"
            );
        }
            User newUser = userRepository.save(User.builder().username(signUpRequestDto.getUsername()).password(passwordEncoder.encode(signUpRequestDto.getPassword())).build());
            return new SignupResponseDto(newUser.getId(),newUser.getUsername());
    }
}
