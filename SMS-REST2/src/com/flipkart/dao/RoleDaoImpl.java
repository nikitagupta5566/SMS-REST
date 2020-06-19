package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.client.UserClient;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.utils.DBUtil;

public class RoleDaoImpl implements RoleDao{
	Connection conn = null;
	PreparedStatement stmt = null;
	private static Logger logger = Logger.getLogger(UserClient.class);
	
	public String getRoleName(int roleId)
	{
		String roleName = null;
		conn = DBUtil.getConnection();
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.GET_ROLE_NAME);
			stmt.setInt(1, roleId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				roleName =rs.getString("name");
			}
			
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		
		return roleName;
	}
	
	
	public int getRoleId(String roleName)
	{
		int roleId = -1;
		conn = DBUtil.getConnection();
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.GET_ROLE_ID);
			stmt.setString(1, roleName);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				roleId =rs.getInt("roleId");
			}
			
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		
		return roleId;
	}

}
