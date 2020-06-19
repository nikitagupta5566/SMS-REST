package com.flipkart.exception;

public class CourseIdAlreadyTakenException extends Exception{
	int courseId;
	
	public CourseIdAlreadyTakenException(int courseId)
	{
		this.courseId = courseId;
	}
	
	public String getMessage()
	{
		return "Course with id " + courseId +  " already taken";
	}

}
