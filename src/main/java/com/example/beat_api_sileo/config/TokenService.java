package com.example.beat_api_sileo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.beat_api_sileo.domain.User.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
         Algorithm algorithm = Algorithm.HMAC256(secret);

         String token = JWT.create()
                 .withIssuer("auth-with-jwt")
                 .withSubject(user.getEmail())
                 .withClaim("userId", user.getId().toString())
                 .withExpiresAt(getExpirationDate())
                 .withIssuedAt(Instant.now())
                    .sign(algorithm);

         return token;
        }

        catch (Exception e) {
            throw new RuntimeException("Error generating token", e);
        }

    }

    public String validateJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid token", e);
        }
    }

    public Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
}
