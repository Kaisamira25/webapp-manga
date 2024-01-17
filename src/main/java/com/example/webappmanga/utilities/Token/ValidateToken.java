package com.example.webappmanga.utilities.Token;

import com.example.webappmanga.utilities.KeyReader.KeyReader;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidateToken {
    public boolean validateToken(String token){
        try {
            // Tiến hành giải mã token giải mã bằng public key thành công không có lỗi thì trả về true
            Jwts.parser()
                    .setSigningKey(KeyReader.PUBLIC_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch (Exception e){
            log.error("ValidateToken: validateToken | error: {}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
