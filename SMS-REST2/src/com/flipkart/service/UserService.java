package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.bean.User;
import com.flipkart.client.UserClient;
import com.flipkart.dao.UserDao;
import com.flipkart.dao.UserDaoImpl;
import com.flipkart.exception.LoginException;
import com.flipkart.exception.UsernameAlreadyTakenException;

public class UserService implements UserInterface{
	private static Logger logger = Logger.getLogger(UserClient.class);
	UserDao userdao = new UserDaoImpl();
	
	// Verify login credentials of user
	public void login(User user) throws LoginException {
			
			int roleId;
			userdao.login(user);
			roleId = user.getRoleId();
			
			if(roleId == -1)
			{
				throw new LoginException();
			}	
	}

	public void createUser(User user, String role) throws UsernameAlreadyTakenException{
		// TODO Auto-generated method stub
			UserDao userDao = new UserDaoImpl();
			userDao.createUser(user,role);
		
	}
	
}
