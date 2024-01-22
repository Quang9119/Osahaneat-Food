package com.cybersoft.Osahaneat.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtilsHelper {
    @Value("${jwt.privateKey}")
    private String privateKey;
    public String generateToken(String data) {
        System.out.println(data);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        return Jwts.builder().subject(data).signWith(key).compact();
    }
    public boolean verifyToken(String token) {
        Jws<Claims> jws;
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            jws = Jwts
                    .parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            System.out.println(jws);
            return  true;
    }
}
