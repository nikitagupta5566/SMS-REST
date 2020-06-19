package com.flipkart.bean;

public class Course {
	String courseName;
	int courseId;
	String courseDescription;
	Float price;
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String coursename) {
		this.courseName = coursename;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseid) {
		this.courseId = courseid;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
}
