package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;
import com.flipkart.client.UserClient;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.utils.DBUtil;
import java.sql.ResultSet;

public class CatalogDaoImpl implements CatalogDao{
	private static Logger logger = Logger.getLogger(UserClient.class);
	Connection conn = null;
	PreparedStatement stmt = null;
	
	// A function that returns all the courses along with details like price and description of the course.
	public List<Course> fetchCatalog()
	{
		conn = DBUtil.getConnection();
		Course course;
		List<Course> course_list = new ArrayList<Course>();
		try
		{
			stmt = conn.prepareStatement(SQLConstantQueries.SELECT_ALL_COURSES);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("name"));
				course.setCourseDescription(rs.getString("description"));
				course.setPrice(rs.getFloat("price"));
				course_list.add(course);
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
		return course_list;
	}
}
