package com.flipkart.client;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.service.DateAndTime;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorService;

public class ProfessorClient implements RootClient{
	private static Logger logger = Logger.getLogger(UserClient.class);
	Scanner sc = new Scanner(System.in);
	ProfessorInterface professorOperation = new ProfessorService();
	String s = "";
	// show menu for professor
	public void showMenu()
	{
//		logger.info("1. View Courses\n2. Select Course\n3. Record Grades\n4. View Student Details\n5. Exit");
		
		logger.info("============================");
	    logger.info("|      PROFESSOR MENU      |");
	    logger.info("============================");
	    logger.info("|  1. View Courses         |");
	    logger.info("|  2. Select Course        |");
	    logger.info("|  3. Record Grades        |");
	    logger.info("|  4. View Student Details |");
	    logger.info("|  5. Allotted Courses     |");
	    logger.info("|  6. Exit                 |");
	    logger.info("============================");
	}
	
	public void main(User user)
	{
		showMenu();
		
		while(true)
		{
			logger.info("Enter Choice");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					logger.info("******** List of Courses ********");
					printCourses(professorOperation.viewAllCourses());
					break;
				case 2:
					logger.info("Enter Course Id of the course you want to teach: ");
					professorOperation.selectCourse(user.getId(),sc.nextInt());
					break;
				case 3:
					logger.info("Enter Course Id, username of student and grade of student");
					professorOperation.submitGrades(sc.nextInt(),sc.next(),sc.next());
					break;
				case 4:
					logger.info("Enter Course Id:");
					printEnrolledStudents(professorOperation.viewEnrolledStudents(sc.nextInt(),user.getId()));
					break;
				case 5:
					printAllottedCourses(professorOperation.viewAllottedCourses(user.getId()));
					break;
				case 6:
					logger.info("Enter Course Id: ");
					logger.info(DateAndTime.getCurrentDate() + "  " + DateAndTime.getCurrentTime() + " " + DateAndTime.getDayOfWeek() + ": Successfully logged out" );	
			}
		}
	}
	
	
	void printEnrolledStudents(List<User> userList)
	{
		if(userList == null)
		{
			logger.info("Either no students are enrolled in this course or You dont teach this course!!!!");
			return;
		}
		
		s = "\n==========================" + String.format("%-15s", "\nUserID")
		+ String.format("%-15s", "Username") + "\n==========================" ;
		
		userList.forEach(user -> { 
			s = s + String.format("\n%-15s", user.getId()) + String.format("%-15s", user.getUsername());
		});
		
		logger.info(s);
	}
	
	void printAllottedCourses(List<Course>allotedCourseList)
	{
		s = "\n===========================================" 
				+ "\n" 
				+ String.format("%-20s", "Id") 
				+ String.format("%-20s","Name") 
				+ "\n==========================================";
		
		allotedCourseList.forEach(course ->
		{
			s = s + "\n" + String.format("%-20s", course.getCourseId()) 
			+ String.format("%-20s", course.getCourseName());
		});
		
		logger.info(s);
	}
}
