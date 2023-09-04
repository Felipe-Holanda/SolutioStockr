package com.techsolutio.solutiostockr.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.techsolutio.solutiostockr.models.entity.Users;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;


    public String generateToken(Users user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("SolutioStockr")
                .withSubject(user.getLogin())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);

            return token;
        }catch(JWTCreationException ex){
            throw new RuntimeException("Error while generating token", ex);
        }
    }

    public String validateToken(String token){
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("SolutioStockr")
                .build()
                .verify(token)
                .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant();
    }

}
