package com.example.webappmanga.service;

import com.example.webappmanga.model.User;
import com.example.webappmanga.service.serviceInterface.OTPVerificationServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OTPVerificationService implements OTPVerificationServiceI {
    private final UserService userService;

//    public OTPVerificationService(UserService userService) {
//        this.userService = userService;
//    }

    @Override
    public boolean verifyOTP(String email, String otp) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return false;
        }
        return user.getVerificationEmailCode().equals(otp);
    }
}
