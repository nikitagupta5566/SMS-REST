package com.flipkart.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminService;
import com.flipkart.service.DateAndTime;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserService;
import com.flipkart.exception.LoginException;
import com.flipkart.exception.UsernameAlreadyTakenException;


public class UserClient {
	private static Logger logger = Logger.getLogger(UserClient.class);
	
	Scanner sc = new Scanner(System.in);
	
	public void showMenu()
	{
			logger.info("============================");
		    logger.info("|        USER MENU         |");
		    logger.info("============================");
		    logger.info("|  1. Register             |");
		    logger.info("|  2. Login                |");
		    logger.info("|  3. Exit                 |");
		    logger.info("============================");
	}

	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		UserClient userClient = new UserClient();
		StudentClient studentClient = new StudentClient();
		UserService userOperation = new UserService();
		logger.info("***********Welcome To Student Management System***********");

		Scanner sc = new Scanner(System.in);
		try
		{
			userClient.showMenu();
			
			logger.info("Enter your choice:");
			int choice = sc.nextInt();
			String username,password;
			switch(choice)
			{
				case 1:
					User user = new User();
					logger.info("Enter username");
					username = sc.next();
						
					logger.info("Enter password");
					password = sc.next();

					user.setUsername(username);
					user.setPassword(password);
					userOperation.createUser(user,"student");
					studentClient.registerStudent(user);
					break;
				case 2:
					userClient.login();
					break;
				case 3:
			}

		}
		catch(UsernameAlreadyTakenException e)
		{
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
	}


	public void login()
	{
		UserInterface userOperation = new UserService();
		
		String username = null;
		String password=null;
		
		User user=null;
		
		while(true)
			{
				try
				{
					logger.info("Enter username");
					username = sc.next();
					
					logger.info("Enter password");
					password = sc.next();
					
					user = new User();
					user.setPassword(password);
					user.setUsername(username);
					
					userOperation.login(user);
					break;
				}
				catch(LoginException e)
				{
					logger.error(e.getMessage());
				}
			}

			
			logger.info(DateAndTime.getCurrentDate() + "  " + DateAndTime.getCurrentTime() + " " + DateAndTime.getDayOfWeek() + ": Successfully logged in as " + username);
			
			if(user.getRoleId() == 1)
			{
				AdminClient adminClient = new AdminClient();
				adminClient.main(user);
			}
			else if(user.getRoleId() == 2)
			{
				StudentClient studentClient = new StudentClient();
				studentClient.main(user);
			}
			else if(user.getRoleId() == 3)
			{
				ProfessorClient professorClient = new ProfessorClient();
				professorClient.main(user);
			}
		}

}
