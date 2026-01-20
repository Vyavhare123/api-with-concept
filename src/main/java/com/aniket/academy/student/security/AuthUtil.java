package com.aniket.academy.student.security;

import com.aniket.academy.student.Entiity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class AuthUtil {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        log.info("inside AuthUtil class --->getSecretKey ********************** 7");
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }
   // this method create jwt token by combined header Algoritham +payload + signature
    public String generateAccessToken(User user){
        log.info("inside AuthUtil class ---->generateAccessToken token 6");
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId",user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000 * 60 * 60))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUserNameFromToken(String token){
        log.info("inside AuthUtil class ---->getUserNameFromToken token ****************************** 8");
      Claims claims= Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = claims.getSubject();

        return username;
    }
}
