package com.flipkart.client;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseIdAlreadyTakenException;
import com.flipkart.exception.UsernameAlreadyTakenException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminService;
import com.flipkart.service.DateAndTime;

public class AdminClient implements RootClient{
	private static Logger logger = Logger.getLogger(UserClient.class);
	Scanner sc = new Scanner(System.in);
	String s = "";
	String role;
	AdminInterface adminOperation = new AdminService();
	// show menu for admin
	public void showMenu()
	{
		logger.info("============================");
	    logger.info("|        ADMIN MENU        |");
	    logger.info("============================");
	    logger.info("|  1. View all users       |");
	    logger.info("|  2. View all courses     |");
	    logger.info("|  3. Add user             |");
	    logger.info("|  4. Delete User          |");
	    logger.info("|  5. Update User          |");
	    logger.info("|  6. Add Course           |");
	    logger.info("|  7. Delete Course        |");
	    logger.info("|  8. Update Course        |");
	    logger.info("|  9. Generate Report Card |");
	    logger.info("|  10. Exit                |");
	    logger.info("============================");
	}
	
	public void main(User user)
	{
		User newUser = null;
		showMenu();
		
		Course course;
		
		while(true)
		{
			try
			{
					
				logger.info("****** Enter Choice ******");
				int choice = sc.nextInt();
				
				switch(choice)
				{
					case 1:
						logger.info("Enter type of user to display: student, professor, admin");
						String typeOfUser = sc.next();
						
						logger.info(adminOperation.viewAllUsers(typeOfUser));
						break;
					case 2:
						logger.info("******** List of Courses ********");
						printCourses(adminOperation.viewAllCourses());
						
						break;
					case 3:
						newUser = new User();
						logger.info("Enter Username, UserPassword, UserRole");
						newUser.setUsername(sc.next());
						newUser.setPassword(sc.next());
						role = sc.next();
						adminOperation.createUser(newUser,role);
						updateDetails(role,newUser.getUsername());
						
						break;
					case 4:
						logger.info("Enter username of user to delete:");
						adminOperation.deleteUser(sc.next());
						break;
					case 5:
						logger.info("Enter Username of user to update:");
						String username = sc.next();
						role = adminOperation.getUserRole(username);
						updateDetails(role,username);
						break;
					case 6:
						course = new Course();
						logger.info("Enter Course Id, Course Name and Course Description and Course Price");
						course.setCourseId(sc.nextInt());
						course.setCourseName(sc.next());
						course.setCourseDescription(sc.next());
						course.setPrice(sc.nextFloat());
						adminOperation.createCourse(course);
						
						logger.info("Course Created");
						break;
					case 7:
						logger.info("Enter Course Id:");
						adminOperation.deleteCourse(sc.nextInt());
						break;
					case 8:
						course = new Course();
						logger.info("Enter course Id of the course you want to update: ");
						course.setCourseId(sc.nextInt());
						logger.info("Enter Name, Price and description of course: ");
						course.setCourseName(sc.next());
						course.setPrice(sc.nextFloat());
						course.setCourseDescription(sc.next());
						adminOperation.updateCourse(course);
						break;
					case 9:
						logger.info("Enter userId of the user:");
						printReportCard(adminOperation.generateReportCard(sc.nextInt()));
						
						break;
					case 10:
						logger.info(DateAndTime.getCurrentDate() + "  " + DateAndTime.getCurrentTime() + " " + DateAndTime.getDayOfWeek() + ": Successfully logged out" );
						break;		
				}
			}
			catch(UsernameAlreadyTakenException e)
			{
				logger.error(e.getMessage());
			}
			catch(CourseIdAlreadyTakenException e)
			{
				logger.error(e.getMessage());
			}
			catch(SQLException e)
			{
				logger.error(e.getMessage());
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
		}
		
	}
	
	
	void printReportCard(HashMap<Course,String> gradeList)
	{
		try
		{
			s = "\n===============================================" 
					+ "\n" 
					+ String.format("%-20s", "CourseId:") 
					+ String.format("%-20s","Course Name:") 
					+ String.format("%-20s", "Grade")
					+ "\n===============================================";
			
			gradeList.forEach((course,grade) -> {
				if(grade == null)
				{
					logger.info(grade == null ? "Not upd": grade);;
				}
				s = s + String.format("\n%-20s", course.getCourseId()) 
				+ String.format("%-20s",course.getCourseName())
				+ String.format("%-20s", grade == null ? "Not updated yet": grade);
			});
			
			logger.info(s);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}

		
	}

	void updateDetails(String role,String username)
	{
		try
		{
			if(role.equals("student"))
			{
				Student student = getStudentDetails();
				student.setUsername(username);
				adminOperation.updateStudentDetails(student);
			}
			else if(role.equals("professor"))
			{
				Professor professor = getProfessorDetails();
				professor.setUsername(username);
				adminOperation.updateProfessorDetails(professor);
			}
			else if(role.equals("admin"))
			{
				Admin admin = getAdminDetails();
				admin.setUsername(username);
				adminOperation.updateAdminDetails(admin);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}

		
	}
}

