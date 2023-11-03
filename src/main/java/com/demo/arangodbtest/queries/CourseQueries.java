package com.demo.arangodbtest.queries;

public class CourseQueries {
	
	public static final String findAllCourses = "FOR c IN courses RETURN c";
	
	public static final String findCourseById = "FOR c IN courses FILTER c._key == @id RETURN c";
	
	public static final String findCourseByTechnology = "FOR c IN courses FILTER c.technology == @technology RETURN c";
}
