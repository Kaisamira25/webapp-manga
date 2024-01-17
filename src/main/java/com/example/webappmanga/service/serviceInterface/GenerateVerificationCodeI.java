package com.example.webappmanga.service.serviceInterface;

import java.util.Date;

public interface GenerateVerificationCodeI {
    String generateCode();

    Date codeExpiration();
}
