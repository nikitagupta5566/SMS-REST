package com.flipkart.service;

import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Registration;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseIdAlreadyTakenException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.RegistrationCompletedException;

public interface StudentInterface extends ServiceInterface{
	public void addCourse(int courseId,int userId) throws RegistrationCompletedException, CourseIdAlreadyTakenException, CourseLimitExceedException;
	public void dropCourse(int courseId,int userId) throws RegistrationCompletedException;
	public float getBill(int userId) throws RegistrationCompletedException;
	public void register(int userId,int modeId) ;
	public List<Course> viewEnrolledCourses(int userId);
	HashMap<Course, String> viewReportCard(int userId);
	public Registration getRegistrationDetails(int userId);
	public void updateStudentDetails(Student student);
}
