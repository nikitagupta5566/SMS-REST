package com.flipkart.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminService;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorService;

@Path("/professor")
public class ProfessorRestController {
	ProfessorInterface professorOperation = new ProfessorService();
	@GET
	@Path("/catalog")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseList (){
		
		List<Course> courseList=professorOperation.viewAllCourses();
		return courseList;
	}
	
	@POST
	@Path("/selectCourse/{courseId}/{userId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("courseId") int courseId, @PathParam("userId") int userId){
		professorOperation.selectCourse(userId,courseId);
		String result="Course Id: " + courseId + " added.";
	return Response.status(201).entity(result).build();

	}
	
	
}
