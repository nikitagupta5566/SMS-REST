package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.client.UserClient;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.exception.CourseIdAlreadyTakenException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.service.DateAndTime;
import com.flipkart.utils.DBUtil;

public class StudentCourseDaoImpl implements StudentCourseDao{
	DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	private static Logger logger = Logger.getLogger(UserClient.class);
	
	// Adds a course to the student course list
		public void addCourse(int courseId,int userId) throws CourseIdAlreadyTakenException
		{
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			
			try
			{
				stmt = conn.prepareStatement(SQLConstantQueries.ADD_COURSE);
				stmt.setInt(1,userId);
				stmt.setInt(2,courseId);
				stmt.setString(3,DateAndTime.getCurrentDateTime().format(formatter));
				
				int rows = stmt.executeUpdate();
				
			}
			catch(SQLIntegrityConstraintViolationException e)
			{
				throw new CourseIdAlreadyTakenException(courseId);
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
		
		// Drop course from student course list
		public void dropCourse(int course_id,int user_id)
		{
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			
			try
			{
				stmt = conn.prepareStatement(SQLConstantQueries.DROP_COURSE);
				stmt.setInt(1,user_id);
				stmt.setInt(2,course_id);
				int rows = stmt.executeUpdate();
				logger.debug(rows);
				
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			
		}
		
		
		// Get a list of enrolled students in a course
		public List<User> getEnrolledStudents(int courseId) {
			// TODO Auto-generated method stub
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			List<User> userList = new ArrayList<User>();
			User user;
			
			try
			{
				stmt = conn.prepareStatement(SQLConstantQueries.GET_ENROLLED_STUDENTS);
				stmt.setInt(1, courseId);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next())
				{
					user = new User();
					user.setUsername(rs.getString("username"));
					user.setId(rs.getInt("userId"));
					userList.add(user);
				}
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			
			return userList;
			
		}
		
		// Submit grades of a particular student for a course by professor
		@Override
		public void submitGrades(int courseId, String username,String grade) {
			// TODO Auto-generated method stub
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			
			try
			{
				stmt = conn.prepareStatement(SQLConstantQueries.SUBMIT_GRADES);
				stmt.setString(1,grade);
				stmt.setString(2,username);
				stmt.setInt(3,courseId);
				
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
		
		// Get list of enrolled courses by a student
		@Override
		public List<Course> getEnrolledCourses(int userId) {
			// TODO Auto-generated method stub
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			List<Course>courseList = new ArrayList<Course>();
			
			try
			{
				stmt = conn.prepareStatement(SQLConstantQueries.GET_ENROLLED_COURSES);
				stmt.setInt(1,userId);
				
				
				ResultSet rs = stmt.executeQuery();
				Course course;
				while(rs.next())
				{
					course = new Course();
					course.setCourseId(rs.getInt("courseid"));
					course.setCourseName(rs.getString("name"));
					course.setPrice(rs.getFloat("price"));
					courseList.add(course);
				}
							
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			return courseList;
		}
		
		// generate report card of a student
		@Override
		public HashMap<Course,String> generateReportCard(int userId) {
			// TODO Auto-generated method stub
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			HashMap<Course,String> gradeList = new HashMap<Course,String>();
			
			try
			{
				stmt = conn.prepareStatement(SQLConstantQueries.GENERATE_REPORT_CARD);
				stmt.setInt(1,userId);
				Course course;
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next())
				{
					course = new Course();
					course.setCourseId(rs.getInt("courseId"));
					course.setCourseName(rs.getString("name"));
					gradeList.put(course,rs.getString("grade"));
					
				}
				
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			
			return gradeList;
		}
		
		// calculates total fees at the time of registration by a student
		public float calculateFee(int userId)
		{
			float amount = 0;
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			
			try
			{
				stmt = conn.prepareStatement(SQLConstantQueries.CALCULATE_BILL);
				stmt.setInt(1,userId);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next())
				{
					amount = rs.getFloat("amount");
				}
				
				
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
			
			return amount;
		}

		@Override
		public void getNumberOfCourses(int userId) throws CourseLimitExceedException{
			// TODO Auto-generated method stub
			
			Connection conn = DBUtil.getConnection();
			PreparedStatement stmt = null;
			try
			{
				stmt = conn.prepareStatement(SQLConstantQueries.GET_NO_OF_ENROLLED_COURSES);
				stmt.setInt(1,userId);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs != null)
				{
					rs.last();
					System.out.println(rs.getRow());
					if(rs.getRow() == 4 )
						throw new CourseLimitExceedException();
				}
				
				
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			
			
		}
}
