package com.flipkart.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DateAndTime {
	static LocalDate localDate = LocalDate.now();
	static LocalTime localTime = LocalTime.now();
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
}
