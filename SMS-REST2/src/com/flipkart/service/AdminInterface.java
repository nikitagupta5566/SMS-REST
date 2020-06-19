package com.flipkart.service;

import java.sql.SQLException;
import java.util.HashMap;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseIdAlreadyTakenException;
import com.flipkart.exception.UserDoesNotExistException;
import com.flipkart.exception.UsernameAlreadyTakenException;

public interface AdminInterface extends ServiceInterface{
	public void createCourse(Course course) throws CourseIdAlreadyTakenException;
	public void deleteCourse(int course_id) throws SQLException, Exception;
	public void createUser(User user,String role) throws UsernameAlreadyTakenException;
	public void deleteUser(String username) throws UserDoesNotExistException, SQLException;
	public String viewAllUsers(String typeOfUser);
	public void updateUser(User user);
	public void updateStudentDetails(Student student);
	public void updateProfessorDetails(Professor professor);
	public void updateAdminDetails(Admin admin);
	public void updateCourse(Course course);
	HashMap<Course, String> generateReportCard(int userId);
	public String getUserRole(String username) throws UserDoesNotExistException;
}
