package com.example.webappmanga.service;

import com.example.webappmanga.service.serviceInterface.GenerateVerificationCodeI;
import com.example.webappmanga.utilities.Time.WhatTime;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service("CodeVerifyEmail")
@Primary
public class CodeVerifyEmailI implements GenerateVerificationCodeI {
    public static final int expiration_time = 10;
    @Override
    public String generateCode() {
        String code = RandomStringUtils.randomAlphanumeric(6);
        return code.toUpperCase(Locale.ROOT);
    }

    @Override
    public Date codeExpiration() {
        return WhatTime.getTheTimeWhenTokenExpiration(expiration_time);
    }
}
