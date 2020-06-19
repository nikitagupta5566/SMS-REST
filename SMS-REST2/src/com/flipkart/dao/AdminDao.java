package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Admin;

public interface AdminDao {
	public List<Admin> fetchAdmin();
	public void createAdmin(Admin admin);
	public void updateAdmin();
}
