package com.inventory.InventoryManagementSystem.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Util {

	public static LocalDateTime getTimeStamp() {  
		LocalDateTime now = LocalDateTime.now();
		String time=now.toString().split("T")[0]+" "+now.toString().split("T")[1].split("\\.")[0];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
		return dateTime;
	}
	
	public static LocalDateTime stringToDate(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
		return dateTime;
	}

}
