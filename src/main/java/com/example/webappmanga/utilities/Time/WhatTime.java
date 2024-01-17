package com.example.webappmanga.utilities.Time;

import java.util.Calendar;
import java.util.Date;

public class WhatTime {
    public static Date getTheTimeRightNow(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        return new Date(calendar.getTime().getTime());
    }
    public static Date getTheTimeWhenTokenExpiration(int expirationTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
