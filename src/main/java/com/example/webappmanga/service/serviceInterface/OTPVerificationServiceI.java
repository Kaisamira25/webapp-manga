package com.example.webappmanga.service.serviceInterface;

public interface OTPVerificationServiceI {
    boolean verifyOTP(String email, String otp);
}
