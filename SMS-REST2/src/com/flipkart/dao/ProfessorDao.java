package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

public interface ProfessorDao {
	public User convertToUser(User user);
//	public void createProfessor(String username);
	public List<Professor> fetchProfessors();
	public void createProfessor(Professor professor);
}
