package com.flipkart.service;

import com.flipkart.bean.User;
import com.flipkart.exception.LoginException;

public interface UserInterface {
	public void login(User user) throws LoginException;
}
