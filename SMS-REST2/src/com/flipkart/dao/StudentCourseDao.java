package com.flipkart.dao;

import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseIdAlreadyTakenException;
import com.flipkart.exception.CourseLimitExceedException;

public interface StudentCourseDao {
	public void addCourse(int courseId,int userId) throws CourseIdAlreadyTakenException;
	public void dropCourse(int courseId,int userId);
	public List<User> getEnrolledStudents(int courseId);
	public void submitGrades(int courseId, String username, String grade);
	public List<Course> getEnrolledCourses(int userId);
	public HashMap<Course, String> generateReportCard(int userId);
	public float calculateFee(int userId);
	public void getNumberOfCourses(int userId) throws CourseLimitExceedException;
}
