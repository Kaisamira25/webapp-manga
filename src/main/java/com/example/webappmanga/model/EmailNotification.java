package com.example.webappmanga.model;

import com.example.webappmanga.utilities.EmailSending.Notification;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@NoArgsConstructor
@Data
public class EmailNotification extends Notification {
    private String from;
    private String[] bcc;
    private String[] cc;
    private List<File> files;

    public EmailNotification(String to, String subject, String body, String from){
        super(to,subject,body);
        this.from = from;
    }
}
