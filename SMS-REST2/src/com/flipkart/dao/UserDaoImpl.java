package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.utils.DBUtil;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.exception.UserDoesNotExistException;
import com.flipkart.exception.UsernameAlreadyTakenException;
import com.flipkart.bean.User;
import com.flipkart.client.UserClient;

public class UserDaoImpl implements UserDao {
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(UserClient.class);
	
	// get user with entered login credential by user
	public void login(User user)
	{
		conn = DBUtil.getConnection();
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.USER_LOGIN);
			
			stmt.setString(1,user.getUsername());
			stmt.setString(2, user.getPassword());
		
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				user.setRoleId(rs.getInt("roleId"));
				user.setId(rs.getInt("userId"));
			}
			
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
	
	}

	// creates a user
	@Override
	public void createUser(User user,String role) throws UsernameAlreadyTakenException {
		conn = DBUtil.getConnection();
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.CREATE_USER);
			
			stmt.setString(1,user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, role);
			
			int rows = stmt.executeUpdate();
			logger.debug(rows);
		}
		catch(SQLIntegrityConstraintViolationException e)
		{
			throw new UsernameAlreadyTakenException(user.getUsername());
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
	}
	
	
	public void deleteUser(String username) throws SQLException,UserDoesNotExistException
	{
		conn = DBUtil.getConnection();
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.DELETE_USER);
			
			stmt.setString(1,username);
			
			int rows = stmt.executeUpdate();
			
			if(rows == 0)
				throw new UserDoesNotExistException(username);
		}
		catch(SQLException e)
		{
			throw e;
		}
	}

	// fetch a details of all users
	@Override
	public List<User> fetchUsers() {
		List<User>userList = new ArrayList<User>();
		
		conn = DBUtil.getConnection();
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.SELECT_ALL_USERS);
			
			ResultSet rs = stmt.executeQuery();
			User user;
			
			while(rs.next())
			{
				user = new User();
				user.setRoleId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt("id"));
				userList.add(user);
			}
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		return userList;
	}

	@Override
	public String getUserRole(String username) throws UserDoesNotExistException{
		String role = null;
		conn = DBUtil.getConnection();
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.GET_USER_ROLE);
			
			stmt.setString(1,username);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				role = rs.getString("name");
			}
			else throw new UserDoesNotExistException(username);
		}
		catch(SQLException e)
		{
//			return e.getMessage();
		}
		// TODO Auto-generated method stub
		return role;
	}
	
	
}
