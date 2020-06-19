package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.client.UserClient;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.exception.CourseIdAlreadyTakenException;
import com.flipkart.service.DateAndTime;
import com.flipkart.utils.DBUtil;

public class CourseDaoImpl implements CourseDao{
	private static Logger logger = Logger.getLogger(UserClient.class);
	
	// Add a new Course to the list
	public void createCourse(Course course) throws CourseIdAlreadyTakenException {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try
		{
			
			stmt = conn.prepareStatement(SQLConstantQueries.CREATE_COURSE);
			stmt.setInt(1,course.getCourseId());
			stmt.setString(2,course.getCourseName());
			stmt.setString(3, course.getCourseDescription());
			stmt.setFloat(4, course.getPrice());
			stmt.setInt(5, 1);
			int rows = stmt.executeUpdate();
			
		}
		catch(SQLIntegrityConstraintViolationException e)
		{
			throw new CourseIdAlreadyTakenException(course.getCourseId());
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
	}
	
	// Delete a course
	public void deleteCourse(int course_id) throws SQLException,Exception
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.DELETE_COURSE);
			stmt.setInt(1,course_id);
		
			int rows = stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	// Update an existing course details
	public void updateCourse(Course course)
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.UPDATE_COURSE);
			stmt.setString(1, course.getCourseName());
			stmt.setFloat(2, course.getPrice());
			stmt.setString(3, course.getCourseDescription());
			stmt.setInt(4, course.getCourseId());
			int rows = stmt.executeUpdate();
			logger.debug(rows);
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
	}
}
