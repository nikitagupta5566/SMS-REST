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
import com.flipkart.client.UserClient;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.utils.DBUtil;

public class AdminDaoImpl implements AdminDao{
	private static Logger logger = Logger.getLogger(UserClient.class);
	
	// fetch all details of admin
	public List<Admin> fetchAdmin()
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		List<Admin> adminList = new ArrayList<Admin>();
		Admin admin;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.SELECT_ALL_ADMINS);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				admin = new Admin();
				admin.setGender(rs.getString("gender"));
				admin.setName(rs.getString("name"));
				admin.setUsername(rs.getString("username"));
				admin.setId(rs.getInt("userId"));
				admin.setDateOfBirth(rs.getDate("dateOfBirth"));
				adminList.add(admin);
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
		
		return adminList;
	}
	
	public void updateAdmin()
	{
		
	}
	
	//create an admin
	public void createAdmin(Admin admin)
	{
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.CREATE_ADMIN);
			stmt.setString(1, admin.getName());
			stmt.setString(2, admin.getGender());
			stmt.setDate(3, admin.getDateOfBirth());
			stmt.setString(4, admin.getUsername());
			
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
