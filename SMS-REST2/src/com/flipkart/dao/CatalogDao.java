package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;

public interface CatalogDao {
	public List<Course> fetchCatalog();
}
