package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;

public interface StudentDao {
	public User convertToUser(User user);
	public void createStudent(String username);
	public List<Student> fetchStudents();
	public void createStudent(Student student);
}
