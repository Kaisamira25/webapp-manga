package com.example.webappmanga.service;

import com.example.webappmanga.model.EmailNotification;
import com.example.webappmanga.service.serviceInterface.EmailI;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendMailService implements EmailI {
    @Autowired
    JavaMailSender mailSender;

    List<EmailNotification> listEmail = new ArrayList<>();
    @Override
    public void send(EmailNotification mail) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getTitle());
            helper.setText(mail.getBody(),true);
            helper.setReplyTo(mail.getFrom());
            String[] cc = mail.getCc();
            if (cc != null && cc.length > 0){
                helper.setCc(cc);
            }
            String[] bcc = mail.getBcc();
            if (bcc != null && bcc.length > 0){
                helper.setBcc(bcc);
            }
            mailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    @Override
    public void send(String to, String subject, String body, String from) {

    }

    @Override
    public void enqueue(EmailNotification mail) {

    }

    @Override
    public void queue(String to, String subject, String body, String from) {

    }
}
