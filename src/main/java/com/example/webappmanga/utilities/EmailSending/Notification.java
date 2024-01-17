package com.example.webappmanga.utilities.EmailSending;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String to;
    private String title;
    private String body;
}
