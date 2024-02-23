package com.example.webappmanga.utilities.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetTheTime {

	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(calendar.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}
	
	public static String getTokenExpirationTime(int expirationTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE,expirationTime);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(calendar.getTime());
	}
}
