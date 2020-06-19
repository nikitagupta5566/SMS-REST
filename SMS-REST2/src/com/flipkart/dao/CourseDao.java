package com.flipkart.dao;

import java.sql.SQLException;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseIdAlreadyTakenException;

public interface CourseDao {
	
	public void createCourse(Course course) throws CourseIdAlreadyTakenException;
	public void deleteCourse(int course_id) throws SQLException, Exception;
	public void updateCourse(Course course);
}
