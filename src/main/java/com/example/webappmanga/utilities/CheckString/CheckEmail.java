package com.example.webappmanga.utilities.CheckString;

import com.example.webappmanga.service.serviceInterface.CheckStringI;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("CheckEmail")
public class CheckEmail implements CheckStringI {
    private static final String email_regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    @Override
    public boolean isStringValid(String rawEmail) {
        Pattern pattern = Pattern.compile(email_regex);
        Matcher matcher = pattern.matcher(rawEmail);
        return matcher.matches();
    }
}
