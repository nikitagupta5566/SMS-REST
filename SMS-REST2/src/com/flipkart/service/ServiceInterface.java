package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.CatalogDao;
import com.flipkart.dao.CatalogDaoImpl;

public interface ServiceInterface {
	
	// View all Courses
	default public List<Course> viewAllCourses() {
		// TODO Auto-generated method stub
		CatalogDao catalogdao = new CatalogDaoImpl();
		List<Course> courseList = catalogdao.fetchCatalog();
		return courseList;
	}
	
	
	default public void exit()
	{
		
	}
}
