package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotAvailableException;

public interface ProfessorCourseDao {
	public void selectCourse(int userId,int courseId);
	public void checkCourse(int courseId) throws CourseNotAvailableException;
	public boolean verifyProfessor(int courseId,int userId);
	public List<Course> viewAllottedCourses(int userId);
}
