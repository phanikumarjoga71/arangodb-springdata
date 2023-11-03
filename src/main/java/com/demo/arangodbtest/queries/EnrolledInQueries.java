package com.demo.arangodbtest.queries;

public class EnrolledInQueries {
	
	public static final String findAllEnrollments = "FOR e IN enrolledIn RETURN e";
	
	public static final String findEnrollmententById = "FOR e IN enrolledIn FILTER e._key == @id RETURN e";
}
