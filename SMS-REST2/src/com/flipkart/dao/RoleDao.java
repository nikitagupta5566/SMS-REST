package com.flipkart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.utils.DBUtil;

public interface RoleDao {
	String getRoleName(int roleId);
	int getRoleId(String roleName);
}
