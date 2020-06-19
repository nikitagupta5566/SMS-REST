package com.flipkart.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminService;

@Path("/admin")
public class AdminRestController {
	
	AdminInterface adminOperation = new AdminService();
	
	@GET
	@Path("/catalog")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseList (){
		// Get List of courses
		List<Course> courseList=adminOperation.viewAllCourses();
		return courseList;
	}
	
	
	@POST
	@Path("/createCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course){
		// Add new Course to catalog
		String result;
		try
		{
			adminOperation.createCourse(course);
			System.out.println("Hit post Service");

			System.out.println("Course Id " + course.getCourseId());
			System.out.println("Course Name " + course.getCourseName());
			System.out.println("Course Fee " + course.getPrice());
			
			result="Track saved: " + course;
		}
		catch(Exception e)
		{
			return Response.status(201).entity(e).build();
		}
		
	

		return Response.status(201).entity(result).build();

	}
	
	
	@DELETE
	@Path("/deleteCourse/{courseId}")
	public Response dropCourse(@PathParam("courseId") int courseId) {
		// Delete course to catalog
		String result;
		try
		{
			adminOperation.deleteCourse(courseId);

			result="Course Id: " + courseId + " deleted.";
			return Response.status(200).entity(result).build();
		}
		catch(Exception e)
		{
			return Response.status(201).entity(e).build();
		}
	
		
	}
	
	
	@GET
	@Path("/viewUserDetails/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserList (@PathParam("type") String type){
		// Get list of users
		String result = adminOperation.viewAllUsers(type);
		return Response.status(201).entity(result).build();
	}
	
	@PUT
	@Path("/updateCourse")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json")
	public Response updateCourse(Course course){
		// update course in catalog
		 adminOperation.updateCourse(course);
		 String result = "Updated";
		return Response.status(201).entity(result).build();
	}
	
}
