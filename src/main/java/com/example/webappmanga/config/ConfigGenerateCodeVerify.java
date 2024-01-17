package com.example.webappmanga.config;

import com.example.webappmanga.service.VerificationCodeManager;
import com.example.webappmanga.service.serviceInterface.GenerateVerificationCodeI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigGenerateCodeVerify {
    @Bean("CodeEmail")
    @Primary
    public VerificationCodeManager codeEmail(@Qualifier("CodeVerifyEmail") GenerateVerificationCodeI generateVerificationCodeI){
        return new VerificationCodeManager(generateVerificationCodeI);
    }
    @Bean("CodePassword")
    public VerificationCodeManager codePassword(@Qualifier("CodeVerifyPassword") GenerateVerificationCodeI generateVerificationCodeI){
        return new VerificationCodeManager(generateVerificationCodeI);
    }
}
