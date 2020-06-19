package com.flipkart.exception;

public class UserDoesNotExistException extends Exception{
	String username;
	
	public UserDoesNotExistException(String username)
	{
		this.username =username;
	}
	
	public String getMessage()
	{
		return "User with username " + username + " does not exist";
	}
	
}
