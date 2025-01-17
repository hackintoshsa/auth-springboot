package com.hackintoshsa.starter.security;

import com.hackintoshsa.starter.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtConfig {
    @Autowired
    private Environment env;

    public String generateToken(User user) {
        long expirationTime = 1000 * 60 * 60; // 1 hour in milliseconds

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, env.getProperty("jwt.secret"))
                .compact();
    }
}
