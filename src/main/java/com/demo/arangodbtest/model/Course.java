package com.demo.arangodbtest.model;

import java.util.Collection;
import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import com.arangodb.springframework.annotation.Relations.Direction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document("courses")
public class Course {

	@Id // db document field: _key
	private String id;

	@ArangoId // db document field: _id
	private String arangoId;
	private String courseName;
	private String technology;

	@Relations(edges = EnrolledIn.class, lazy = true, direction = Direction.INBOUND)
	@JsonIgnoreProperties("courses")
	private Collection<Student> students;

	public Course(String id, String arangoId, String courseName, String technology, Collection<Student> students) {
		super();
		this.id = id;
		this.arangoId = arangoId;
		this.courseName = courseName;
		this.technology = technology;
		this.students = students;
	}

	public Course() {
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", arangoId=" + arangoId + ", courseName=" + courseName + ", technology="
				+ technology + ", students=" + students + "]";
	}

}
