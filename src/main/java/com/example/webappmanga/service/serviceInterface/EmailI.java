package com.example.webappmanga.service.serviceInterface;

import com.example.webappmanga.model.EmailNotification;

public interface EmailI {
    void send(EmailNotification mail);
    void send(String to, String subject, String body, String from);
    void enqueue(EmailNotification mail);
    void queue(String to, String subject, String body, String from);
}
