package com.demo.arangodbtest.queries;

public class StudentQueries {
	
	public static final String findAllStudents = "FOR s IN students RETURN s";
	
	public static final String findStudentById = "FOR s IN students FILTER s._key == @id RETURN s";
	
	public static final String enrollForCourse = "FOR student IN students FILTER student._key == @studentId " +
		       								  "FOR course IN courses FILTER course._key == @courseId " +
		       								  "INSERT { _from: student._id, _to: course._id } INTO enrolledIn RETURN NEW";
			
			
}
