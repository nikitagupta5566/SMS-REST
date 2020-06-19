package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.client.UserClient;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.service.DateAndTime;
import com.flipkart.utils.DBUtil;

public class StudentDaoImpl implements StudentDao{
	private static Logger logger = Logger.getLogger(UserClient.class);
	public User convertToUser(User user)
	{
		Student student = new Student();
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.GET_STUDENT);
			stmt.setInt(1, user.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			student.setUsername(user.getUsername());
			student.setPassword(user.getPassword());
			student.setId(user.getId());
			student.setRoleId(user.getRoleId());
			while(rs.next())
			{
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
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
		
		return student;
	}
	
	// create a student 
	public void createStudent(String username)
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.CREATE_STUDENT);
			stmt.setString(1, username);
			
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

	// fetches the details of all students
	@Override
	public List<Student> fetchStudents() {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<Student> studentList = new ArrayList<Student>();
		Student student;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.SELECT_ALL_STUDENTS);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				student = new Student();
				student.setGender(rs.getString("gender"));
				student.setName(rs.getString("name"));
				student.setUsername(rs.getString("username"));
				student.setDateOfBirth(rs.getDate("dateOfBirth"));
				student.setId(rs.getInt("userId"));
				studentList.add(student);
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
		
		return studentList;
	}
	
	// creates a student 
	public void createStudent(Student student)
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.CREATE_STUDENT);
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getGender());
			stmt.setDate(3, student.getDateOfBirth());
			stmt.setString(4, student.getUsername());
			
			stmt.executeUpdate();
			
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
