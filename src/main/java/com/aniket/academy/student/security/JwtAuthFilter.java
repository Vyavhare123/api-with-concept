package com.aniket.academy.student.security;

import com.aniket.academy.student.Entiity.User;
import com.aniket.academy.student.exception.UsernameNotFoundException;
import com.aniket.academy.student.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final AuthUtil authUtil;
   // This for handle the jwt global exception
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("inside JwtAuthFilter --->doFilterInternal *********************** 2");
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = requestTokenHeader.split("Bearer ")[1];
            String userNameFromToken = authUtil.getUserNameFromToken(token);

            if (userNameFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByUsername(userNameFromToken).orElseThrow(() -> new UsernameNotFoundException(userNameFromToken));
                UsernamePasswordAuthenticationToken usernamePasswordAutheticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAutheticationToken);

            }
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException | AuthenticationException | JwtException | AccessDeniedException exception ){
            handlerExceptionResolver.resolveException(request,response,null,exception);
        }catch(Exception ex){
            log.info(ex.getMessage());
        }

    }
}
