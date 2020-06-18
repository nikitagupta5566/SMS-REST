package com.flipkart.exception;

public class UsernameAlreadyTakenException extends Exception{
	String username;
	
	public UsernameAlreadyTakenException(String username)
	{
		this.username = username;
	}
	public String getMessage()
	{
		return "Username " + username + " already taken";
	}
}
