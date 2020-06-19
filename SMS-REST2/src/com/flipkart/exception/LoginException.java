package com.flipkart.exception;

public class LoginException extends Exception{
	public String getMessage()
	{
		return "Wrong Username or password";
	}
}
