package com.demo.arangodbtest.model;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

@Edge
public class EnrolledIn {
	
	@Id
    private String id;

    @From(lazy=true)
    private Student student;

    @To(lazy=true)
    private Course course;

	public EnrolledIn(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
	
	public EnrolledIn() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "EnrolledIn [id=" + id + ", student=" + student + ", course=" + course + "]";
	}
   
}
