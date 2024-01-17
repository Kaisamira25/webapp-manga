package com.example.webappmanga.utilities.CustomUser;

import com.example.webappmanga.utilities.KeyReader.KeyReader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetUserInfo {
    public Integer getUserId(String token){
        try {
            // Nhận vào một token và phân tích token để lấy ra phần payload có trong token
            // Sử dụng parser để giải mã token
            Claims claims = Jwts.parser()
                    .setSigningKey(KeyReader.PUBLIC_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            log.info("GetUserInfo: getUserId | UserId: {}",claims.get("userId",Integer.class));
            return claims.get("userId",Integer.class);
        }catch (Exception e){
            log.error("GetUserInfo: getUserId | error: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
