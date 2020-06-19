package com.flipkart.exception;

public class CourseLimitExceedException extends Exception{
	
	public String getMessage()
	{
		return "You can add maximum 4 courses!!";
	}

}
