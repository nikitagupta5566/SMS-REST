package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.client.UserClient;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.utils.DBUtil;

public class ProfessorDaoImpl implements ProfessorDao{
	private static Logger logger = Logger.getLogger(UserClient.class);
	public User convertToUser(User user)
	{
		Professor professor = new Professor();
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.GET_STUDENT);
			stmt.setInt(1, user.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			professor.setUsername(user.getUsername());
			professor.setPassword(user.getPassword());
			professor.setId(user.getId());
			professor.setRoleId(user.getRoleId());
			
			while(rs.next())
			{
				professor.setName(rs.getString("name"));
				professor.setGender(rs.getString("gender"));
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
		
		return professor;
	}
	
	
//	public void createProfessor(String username)
//	{
//		Connection conn = DBUtil.getConnection();
//		PreparedStatement stmt = null;
//		
//		try
//		{
//			stmt = conn.prepareStatement(SQLConstantQueries.CREATE_PROFESSOR);
//			stmt.setString(1, username);
//			stmt.setString(, arg1);
//			int rows = stmt.executeUpdate();
//			logger.debug(rows);
//		}
//		catch(SQLException e)
//		{
//			logger.error(e.getMessage());
//		}
//		catch(Exception e)
//		{
//			logger.error(e.getMessage());
//		}
//	}
	
	public List<Professor> fetchProfessors()
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<Professor> professorList = new ArrayList<Professor>();
		Professor professor;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.SELECT_ALL_PROFESSORS);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				professor = new Professor();
				professor.setGender(rs.getString("gender"));
				professor.setName(rs.getString("name"));
				professor.setUsername(rs.getString("username"));
				professor.setDateOfBirth(rs.getDate("dateOfBirth"));
				professor.setId(rs.getInt("userId"));
//				professor.setUsername(rs.getString("username"));
				professorList.add(professor);
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
		
		return professorList;	
	}
	
	public void createProfessor(Professor professor)
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.CREATE_PROFESSOR);
			stmt.setString(1, professor.getName());
			stmt.setString(2, professor.getGender());
			stmt.setDate(3, professor.getDateOfBirth());
			stmt.setString(4, professor.getUsername());
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
