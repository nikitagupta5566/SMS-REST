package com.flipkart.constant;

public class SQLConstantQueries {
	public static final String USER_LOGIN = "select * from user where username = ? and password = ?";
	public static final String SELECT_ALL_USERS = "select * from user";
	public static final String SELECT_ALL_COURSES = "select * from course";
	public static final String ADD_COURSE = "insert into studentCourse(userId,courseId,date) values(?,?,?)";
	public static final String DROP_COURSE = "delete from studentCourse where userid = ? and courseid = ?";
	public static final String CREATE_COURSE = "insert into course values(?,?,?,?,?)";
	public static final String DELETE_COURSE = "delete from course where courseId = ?";
	public static final String CREATE_USER = "insert into user(username,password,roleId) values (?,?,(select roleId from role where name=?));";
	public static final String DELETE_USER = "delete from user where username = ?";
	public static final String REGISTER_COURSE = "insert into registration(userId,timestamp,paymentId) values(?,?,?)";
	public static final String CHECK_REGISTRATION = "select userId from registration where userId = ?";
	public static final String SELECT_COURSE_TO_TEACH = "insert into professorCourse(userId,courseId) values(?,?)";
	public static final String GET_PROFESSOR_COURSE ="select * from professorCourse where courseId = ?";
	public static final String GET_ENROLLED_STUDENTS = "select * from studentCourse join user on studentCourse.userId = user.userId where courseid = ?";
	public static final String SUBMIT_GRADES = "update course set grade = ? where userid = (select userid from user where user.username = ?) and courseid = ?";
	public static final String GET_ENROLLED_COURSES = "select * from course join studentCourse on course.courseId = studentCourse.courseId where userid = ?";
	public static final String GENERATE_REPORT_CARD = "select * from course join studentCourse on course.courseId = studentCourse.courseId where userId = ?";
	public static final String GET_ROLE_NAME = "select name from role where roleId = ?";
	public static final String GET_ROLE_ID = "select roleId from role where name = ?";
	public static final String GET_STUDENT = "select * from student where userId = ?";
	
	public static final String CREATE_PROFESSOR = "update professor set name = ?,gender = ?,dateOfBirth = ? where userId = (select userId from user where username = ?) ";
	public static final String SELECT_ALL_STUDENTS = "select * from student join user on student.userId = user.userId";
	public static final String SELECT_ALL_PROFESSORS = "select * from professor join user on professor.userId = user.userId";
	public static final String SELECT_ALL_ADMINS = "select * from admin join user on admin.userId = user.userId";
	public static final String CALCULATE_BILL = "select sum(price) as amount from studentCourse join course on course.courseId = studentCourse.courseId where userId = ? ";
	public static final String GET_ALLOTED_COURSES = "select * from professorCourse join course on course.courseId = professorCourse.courseId where userId = ?";
	public static final String CREATE_STUDENT = "update student set name = ?,gender = ?,dateOfBirth = ? where userId = (select userId from user where username = ?) ";
	public static final String CREATE_ADMIN = "update admin set name = ?,gender = ?,dateOfBirth = ? where userId = (select userId from user where username = ?) ";
	
	public static final String UPDATE_COURSE = "update course set name = ?,price = ?, description = ? where courseId = ?";
	public static final String GET_REGISTRATION_DETAILS = "select * from registration where userId = ?";
	
	public static final String GET_USER_ROLE = "select name from user join role on role.roleId = user.roleId where username = ?";
	
	public static final String GET_NO_OF_ENROLLED_COURSES = "select * from studentCourse where userId = ?";
}
