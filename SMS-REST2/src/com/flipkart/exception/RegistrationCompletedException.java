package com.flipkart.exception;

public class RegistrationCompletedException extends Exception{
	String message = "########  Registration already completed!!!! #######";
	public String getMessage()
	{
		return message;
	}
	
	
}
