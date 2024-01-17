package com.example.webappmanga.service;

import com.example.webappmanga.service.serviceInterface.GenerateVerificationCodeI;
import com.example.webappmanga.utilities.Time.WhatTime;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service("CodeVerifyPassword")
public class CodeVerifyPasswordI implements GenerateVerificationCodeI {
    private static final int expiration_time = 10;

    @Override
    public String generateCode() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Date codeExpiration() {
        return WhatTime.getTheTimeWhenTokenExpiration(expiration_time);
    }
}
