package com.example.webappmanga.service;

import com.example.webappmanga.dto.request.SignUpDTO;
import com.example.webappmanga.model.EmailNotification;
import com.example.webappmanga.model.User;
import com.example.webappmanga.service.serviceInterface.*;
import com.example.webappmanga.utilities.Time.WhatTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpService implements SendNotificationI<User> {
    private final UserService userService;
    private final EmailI emailI;
    private final CreateAndUpdateI<Integer, User> createUser;
    private final PasswordEncoder encoder;
    @Qualifier("CheckPassword")
    private final CheckStringI checkStringPassword;
    @Qualifier("CheckEmail")
    private final CheckStringI checkStringEmail;
    @Qualifier("CodeEmail")
    private final VerificationCodeManager codeVerificationEmail;
    @Value("${email_root}")
    private String email_root;

    private final SendMailTemplateService sendMailTemplateService;
    public static final String template_verify_code_sign_up = "templateVerifyCodeSignUp";
    private static String message_notification = "Please click verify your account: ";
    @Transactional
    public Integer register(SignUpDTO signUpDTO){
        if (userService.findByEmail(signUpDTO.email()) == null){
            if (checkStringPassword.isStringValid(signUpDTO.password()) == false
                || checkStringEmail.isStringValid(signUpDTO.email()) == false){
                return 0;
            }
            User user = new User();
            user.setEmail(signUpDTO.email());
            user.setFullName(signUpDTO.fullName());
            user.setPassword(encoder.encode(signUpDTO.password()));
            user.setCreatedAt(WhatTime.getTheTimeRightNow());
            user.setVerificationEmailCode(codeVerificationEmail.generateCode());
            user.setVerificationEmailCodeExpiration(codeVerificationEmail.codeExpiration());
            user.setRemainingVerification(5);
            log.info("-----> SignUpService | register: sign up with email {}",user.getEmail());
            createUser.create(user);
            sendEmail(user,user.getVerificationEmailCode());
            return 1;
        }else {
            return 2;
        }
    }
    @Override
    public void sendEmail(User content, String code) {
//        String paramCode = url + "/api/v1/auth/verify?code=" + content.getVerificationEmailCode();
        log.info("-----> SignUpService | sendEmail: Code for verify {}",code);
        var email = new EmailNotification();
        email.setFrom(email_root);
        email.setTo(content.getEmail());
        email.setTitle("Email Verification");
        email.setBody(sendMailTemplateService.sendEmailWithTemplate(email.getTitle(),
                message_notification + content.getEmail(),
                code,template_verify_code_sign_up));
        emailI.send(email);
    }
}
