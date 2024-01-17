package com.example.webappmanga.service;

import com.example.webappmanga.service.serviceInterface.GenerateVerificationCodeI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VerificationCodeManager {
    private final GenerateVerificationCodeI emailVerification;

    @Autowired
    public VerificationCodeManager(GenerateVerificationCodeI emailVerification){
        this.emailVerification = emailVerification;
    }
    public String generateCode(){
        return this.emailVerification.generateCode();
    }
    public Date codeExpiration(){
        return this.emailVerification.codeExpiration();
    }
}
