package com.example.basic.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtHelper {
    @Value("${jwt.secret}")
    private String secret;

    public boolean verifyToken(String token) {
        boolean result = false;
        Key key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(secret.getBytes()));
        try {
            Jwts.parser().setSigningKey(key).build().parseSignedClaims(token);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
