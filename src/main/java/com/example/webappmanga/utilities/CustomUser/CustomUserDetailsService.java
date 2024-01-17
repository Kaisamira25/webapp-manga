package com.example.webappmanga.utilities.CustomUser;

import com.example.webappmanga.model.User;
import com.example.webappmanga.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    // Tiêm phụ thuộc vào bằng constructor injection với sự trợ giúp của @RequiredArgsConstructor
    private final UserRepository useRepo;

    // Phương thức loadUserByUsername được gọi mỗi khi người dùng cố gắng thực hiện đăng nhập
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm user bằng email nhận được
        User user = useRepo.findByEmail(username);

        // Nếu user tìm thấy thì trả về 1 CustomUserDetails với user được tìm thấy trong data
        if(user != null){
            return new CustomUserDetails(user);
        }
        log.error("CustomUserDetailsService: loadUserByUsername | User not found with the given email : {}", username);
        throw new UsernameNotFoundException("User not found with email : " + username);
    }
}
