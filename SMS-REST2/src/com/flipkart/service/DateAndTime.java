package com.flipkart.service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class DateAndTime {
	static LocalDate localDate = LocalDate.now();
	static LocalTime localTime = LocalTime.now();
	static LocalDateTime localDateTime = LocalDateTime.now();
	
	public static LocalDate getCurrentDate()
	{
		return localDate;
		
	}
	
	public static LocalTime getCurrentTime()
	{
		
		return localTime;
	}
	
	public static DayOfWeek getDayOfWeek()
	{
		LocalDate localDate = LocalDate.now();
		return localDate.getDayOfWeek();
	}
	
	public static LocalDateTime getCurrentDateTime()
	{
		return localDateTime;
	}
}
