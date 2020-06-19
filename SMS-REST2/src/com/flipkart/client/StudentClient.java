package com.flipkart.client;

import java.util.HashMap;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Registration;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.dao.StudentDao;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.exception.CourseIdAlreadyTakenException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.RegistrationCompletedException;
import com.flipkart.service.DateAndTime;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;

public class StudentClient implements RootClient{
	private static Logger logger = Logger.getLogger(UserClient.class);
	Scanner sc = new Scanner(System.in);
	StudentDao studentDao = new StudentDaoImpl();
	String s;
	// shows menu for student
	public void showMenu()
	{
		logger.info("===============================");
		logger.info("|         STUDENT MENU       	|");
		logger.info("===============================");
	    logger.info("|  1. Request Catalog       	|");
	    logger.info("|  2. Add Course            	|");
	    logger.info("|  3. Drop Course           	|");
	    logger.info("|  4. Regiser               	|");
	    logger.info("|  5. View Registration Details  |");
	    logger.info("|  6. View Enrolled Courses 	|");
	    logger.info("|  7. View Report Card      	|");
	    logger.info("|  8. Exit                  	|");
	    logger.info("================================");
	}
	
	public void main(User user)
	{
		showMenu();
		
		StudentInterface studentOperation = new StudentOperation();
		int courseId;
		
		while(true)
		{
			try
			{
				logger.info("Enter Choice");
				int choice = sc.nextInt();
				
				switch(choice)
				{
					case 1:
						logger.info("******** List of Courses ********");
						printCourses(studentOperation.viewAllCourses());
						break;
					case 2:
						logger.info("Enter course_id of the course you want to add:");
						courseId = sc.nextInt();
						studentOperation.addCourse(courseId,user.getId());
//						logger.info("######  Successfully added course with id:" + courseId + "#######");
						break;
					case 3:
						logger.info("Enter course_id of the course you want to delete:");
						courseId = sc.nextInt();
						studentOperation.dropCourse(courseId,user.getId());
//						logger.info("###### Successfully deleted course with id:" + courseId + "######");
						break;
					case 4:
						float amount = 0;
						logger.info("Note: After completing registration, you would not be able to add/drop courses");
						logger.info("Do you want to proceed(y/n)");
						if(sc.next().equals("y"))
						{
							logger.info("****** Initiating Registration and Payment Process..... ********");
							amount = studentOperation.getBill(user.getId());
							logger.info("Total payable amount:" + amount);
							
							logger.info("Select Mode of Payment:");
							logger.info("1. Credit Card");
							logger.info("2. Debit Card");
							logger.info("3. Cash");
							
							studentOperation.register(user.getId(),sc.nextInt());
							Registration registration = studentOperation.getRegistrationDetails(user.getId());
							
							printRegistrationDetails(registration);
						}
						
						break;
					case 5:
						Registration registration = studentOperation.getRegistrationDetails(user.getId());
						if(registration.getRegId() == -1)
						{
							logger.info("You have not completed registation yet!!!");
						}
						else
						printRegistrationDetails(registration);
						
						break;
					case 6:
						printCourses(studentOperation.viewEnrolledCourses(user.getId()));
						break;
					case 7:
						printReportCard(studentOperation.viewReportCard(user.getId()));
						break;
					case 8:
						logger.info(DateAndTime.getCurrentDate() + "  " + DateAndTime.getCurrentTime() + " " + DateAndTime.getDayOfWeek() + ": Successfully logged out" );
				}
			}
			catch(RegistrationCompletedException e)
			{
				logger.error(e.getMessage());
			}
			catch(CourseIdAlreadyTakenException e)
			{
				logger.error(e.getMessage());
			}
			catch(CourseLimitExceedException e)
			{
				logger.error(e.getMessage());
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
			}
		}
		
		
		
		
	}
	
	void printRegistrationDetails(Registration registration)
	{
		try
		{
			String s;
			s = "\n===============================================" 
					+ "\n" 
					+ String.format("%-20s", "Registration Id: ") + String.format("%-20s", registration.getRegId()) + "\n" 
					+ String.format("%-20s","Registration Date: ") + String.format("%-20s", registration.getRegistrationDate()) + "\n"
					+ String.format("%-20s", "Payment Id: ") + String.format("%-20s", registration.getPaymentId()) 
					+ "\n===============================================";
			
			logger.info(s);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
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

	public void registerStudent(User user) {
		// TODO Auto-generated method stub
		try
		{
			updateDetails(user.getUsername());
			StudentClient studentClient = new StudentClient();
			studentClient.main(user);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
	}
	
	void updateDetails(String username)
	{
		StudentOperation studentOperation = new StudentOperation();
		try
		{
			Student student = getStudentDetails();
			student.setUsername(username);
			studentOperation.updateStudentDetails(student);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
	}
	
}
