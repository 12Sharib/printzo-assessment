package com.project.product_management.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String secret;

  public String generateToken(String email, String role) {
    return JWT.create()
        .withSubject(email)
        .withClaim("role", "ROLE_" + role)
        .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
        .sign(Algorithm.HMAC256(secret));
  }
}