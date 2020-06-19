package com.flipkart.client;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface RootClient {
	static Logger logger = Logger.getLogger(UserClient.class);
	Scanner sc = new Scanner(System.in);
	default void printCourses(List<Course> courseList)
	{
		String s;
		
		s = "\n===============================================" 
				+ "\n" 
				+ String.format("%-20s", "Id") 
				+ String.format("%-20s","Name")
				+ String.format("%-20s", "Price") 
				+ "\n===============================================";
				
				for(Course course:courseList)
				{
					s = s + "\n" + String.format("%-20s", course.getCourseId()) 
					+ String.format("%-20s", course.getCourseName())
					+ String.format("%-20s", course.getPrice());
				}
				
				logger.info(s);
	}
	
	public void showMenu();
	
	default public Student getStudentDetails()
	{
		Student student = new Student();
		
		logger.info("Enter name,gender and DOB:");
		student.setName(sc.next());
		student.setGender(sc.next());
		student.setDateOfBirth(Date.valueOf(sc.next()));
		
		return student;
	}
	
	default public Professor getProfessorDetails()
	{
		Professor professor = new Professor();
		
		logger.info("Enter name,gender and DOB:");
		professor.setName(sc.next());
		professor.setGender(sc.next());
		professor.setDateOfBirth(Date.valueOf(sc.next()));
		return professor;
	}
	
	default public Admin getAdminDetails()
	{
		Admin admin = new Admin();
		
		logger.info("Enter name,gender and DOB:");
		admin.setName(sc.next());
		admin.setGender(sc.next());
		admin.setDateOfBirth(Date.valueOf(sc.next()));
		
		return admin;
	}
	
	
}
