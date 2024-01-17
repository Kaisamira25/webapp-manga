package com.example.webappmanga.service.serviceInterface;

public interface SendNotificationI<T> {
    void sendEmail(T content, String url);
}
