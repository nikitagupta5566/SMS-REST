package com.flipkart.rest;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Catalog;
import com.flipkart.bean.Course;
import com.flipkart.bean.Registration;
import com.flipkart.service.StudentOperation;

@Path("/student")
public class StudentRestController {
	StudentOperation studentOperation = new StudentOperation();
	
	
	@GET
	@Path("/catalog")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseList (){
		// get list of courses
		List<Course> courseList=studentOperation.viewAllCourses();
		return courseList;
	}
	
	
	@POST
	@Path("/addCourse/{courseId}/{userId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("courseId") int courseId, @PathParam("userId") int userId){
		// add course to enrolled list
		String result = " ";
		try
		{
			studentOperation.addCourse(courseId,userId);
			result="Course Id: " + courseId + " added.";
		}
		catch(Exception e)
		{
			return Response.status(201).entity(e).build();
		}
		
		return Response.status(201).entity(result).build();
	

	}
	
	
	@DELETE
	@Path("/dropCourse/{courseId}/{userId}")
	public Response dropCourse(@PathParam("courseId") int courseId, @PathParam("userId") int userId) {
		// drop course from enrolled list
		String result = " ";
		try
		{
			studentOperation.dropCourse(courseId,userId);
			result = "Course Id: " + courseId + " dropped.";
		}
		catch(Exception e)
		{
			return Response.status(200).entity(e).build();
		}
		

		
		return Response.status(200).entity(result).build();
	}
	
	
	
	@POST
	@Path("/register/{userId}/{modeId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerCourse(@PathParam("userId") int userId,@PathParam("modeId") int modeId){
		// register for the courses
		studentOperation.register(userId,modeId);
		String result="User Id: " + userId + " registered.";
	return Response.status(201).entity(result).build();

	}
	
	
	@GET
	@Path("/registrationDetails/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Registration getCourseList (@PathParam("userId") int userId){
		// get enrolled courses list
		Registration registration=studentOperation.getRegistrationDetails(userId);
		return registration;
	}
	
	@GET
	@Path("/reportCard/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<Course, String> getReportCard (@PathParam("userId") int userId){
		// get report card of student by userId
		return studentOperation.viewReportCard(userId);
	}
}
