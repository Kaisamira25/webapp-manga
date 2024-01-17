package com.example.webappmanga.utilities.Jwt;

import com.example.webappmanga.model.User;
import com.example.webappmanga.repository.UserRepository;
import com.example.webappmanga.utilities.CustomUser.CustomUserDetailsService;
import com.example.webappmanga.utilities.CustomUser.GetUserInfo;
import com.example.webappmanga.utilities.Token.ValidateToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GetUserInfo getUserInfo;
    @Autowired
    private ValidateToken validateToken;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public static String getAccessTokenFromRequestAuthorization(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        // Check Authorization JWT
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
    public static String getRefreshTokenFromCookie(HttpServletRequest request){
        String token = request.getHeader("refreshToken");
        // Cụ thể các hoạt động: Khi người dùng đăng nhập sẽ được nhận 2 token 1 là token cho việc hoạt động ngay lúc đó
        // 2 là refreshToken để khi token hiện tại hết hạn thì token này sẽ lại được gửi đi thay thế cho token trước đó và
        // khi đó sẽ lại được cấp một token refresh mới và khi hết hạn thì refreshToken sẽ lại được gửi để cấp token mới
        // Check Authorization JWT
        if (token != null){ // Nếu token có thì trả lại token đó
            return token;
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtAuthenticationFilter: doFilterInternal | Start");
        try {
            // Lấy token
            String jwt = getAccessTokenFromRequestAuthorization(request);
            // Nếu jwt có giá trị thì thực hiện xác minh token đó (jwt ở đây là token)
            if (StringUtils.hasText(jwt) && validateToken.validateToken(jwt)){
                // Nếu validate thành công thì tiến hành tìm kiếm thông tin người dùng và lấy ra userId từ database
                Integer userId = getUserInfo.getUserId(jwt); // --> Lấy userId từ payload của jwt token
                Optional<User> user = userRepository.findById(userId); // --> Lấy thông tin user bằng userId
                if (user != null){
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.get().getEmail()); // --> Tìm kiếm user bằng email và gán cho một userDetails

                    // Nếu mà user được tìm thấy thì bỏ thông tin đó vào SecuriyContext
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }catch (Exception e){
            log.error("JwtAuthenticationFilter: doFilterInternal | error: {}",e.getMessage());
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);
    }
}
