package com.demo.arangodbtest.model;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import com.arangodb.springframework.annotation.Relations.Direction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document("students")
public class Student {

	@Id // db document field: _key
	private String id;

	@ArangoId // db document field: _id
	private String arangoId;
	private String studentName;
	private String studentEmail;

	@Relations(edges = EnrolledIn.class, lazy = true, direction = Direction.OUTBOUND)
	@JsonIgnoreProperties("students")
	private Collection<Course> courses;


	public Student(String id, String arangoId, String studentName, String studentEmail, Collection<Course> courses) {
		super();
		this.id = id;
		this.arangoId = arangoId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.courses = courses;
	}

	public Student() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArangoId() {
		return arangoId;
	}

	public void setArangoId(String arangoId) {
		this.arangoId = arangoId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public Collection<Course> getCourses() {
		if (this.courses == null) {
			this.courses = new ArrayList<>();
		}
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", arangoId=" + arangoId + ", studentName=" + studentName + ", studentEmail="
				+ studentEmail + ", courses=" + courses + "]";
	}

}
