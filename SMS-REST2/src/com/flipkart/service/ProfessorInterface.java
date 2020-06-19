package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;

public interface ProfessorInterface extends ServiceInterface{
	public void submitGrades(int courseId, String username,String grade);
	public List<User> viewEnrolledStudents(int courseId,int userId);
	public void selectCourse(int userId,int courseId);
	public List<Course> viewAllottedCourses(int userId);
}
