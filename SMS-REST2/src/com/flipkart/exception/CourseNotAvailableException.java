package com.flipkart.exception;

public class CourseNotAvailableException extends Exception{
	public String getMessage()
	{
		return "Course already selected! Try selecting some other course !";
	}

}
