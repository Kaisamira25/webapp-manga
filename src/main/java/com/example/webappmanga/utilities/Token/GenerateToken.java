package com.example.webappmanga.utilities.Token;

import com.example.webappmanga.utilities.CustomUser.CustomUserDetails;
import com.example.webappmanga.utilities.KeyReader.KeyReader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class GenerateToken {
    @Value("${jwt.expiration}")
    private int JWT_EXPIRATION;

    @Value("${jwt.refreshtoken.expiration}")
    private long JWT_REFRESH_EXPIRATION;

    public String generateAccessToken(CustomUserDetails customUserDetails){
        try {
            Date now = new Date();
            Date dateExpiration = new Date(now.getTime() + JWT_EXPIRATION);

            return Jwts.builder()
                    .claim("role",customUserDetails.getAuthorities())
                    .setIssuedAt(now)
                    .setExpiration(dateExpiration)
                    .signWith(SignatureAlgorithm.RS256, KeyReader.PRIVATE_KEY)
                    .compact();
        }catch (Exception e){
            log.error("GenerateToken: generateAccessToken | Error: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String generateRefreshToken(CustomUserDetails customUserDetails){
        try {
            Date now = new Date();
            Date dateExpiration = new Date(now.getTime() + JWT_REFRESH_EXPIRATION);

            return Jwts.builder()
                    .claim("role",customUserDetails.getAuthorities())
                    .claim("userId",customUserDetails.getUserId())
                    .setIssuedAt(now)
                    .setExpiration(dateExpiration)
                    .signWith(SignatureAlgorithm.RS256,KeyReader.PRIVATE_KEY)
                    .compact();
        }catch (Exception e){
            log.error("");
            e.printStackTrace();
        }
        return null;
    }
}
