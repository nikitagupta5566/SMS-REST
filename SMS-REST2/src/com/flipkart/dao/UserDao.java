package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.UserDoesNotExistException;
import com.flipkart.exception.UsernameAlreadyTakenException;

public interface UserDao {
	public void login(User user);
	public void createUser(User user,String role) throws UsernameAlreadyTakenException;
	public void deleteUser(String username) throws SQLException, UserDoesNotExistException;
	public List<User> fetchUsers();
	public String getUserRole(String username) throws UserDoesNotExistException;
}
