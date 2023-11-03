package com.demo.arangodbtest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.arangodbtest.model.Course;
import com.demo.arangodbtest.model.Student;
import com.demo.arangodbtest.repository.CourseRepository;
import com.demo.arangodbtest.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public List<Student> getAllStudents(){
		List<Student> students=new ArrayList<Student>();
		studentRepository.findAll().forEach  (students::add); 
	    System.out.println(students.toArray().toString());
	      return students;
	}

//	@GetMapping
//	public ResponseEntity<Iterable<BaseDocument>> getAllStudents() {
//		Iterable<BaseDocument> students = studentRepository.getAllStudents();
//		if (students.iterator().hasNext()) {
//			return new ResponseEntity<>(students, HttpStatus.FOUND);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable String id){
		Optional<Student> optStudent = studentRepository.findById(id);
		if (optStudent.isPresent()) {
			Student student = optStudent.get();
			System.out.println("student " + student);
			return student;
		} else {
			return null;
		}
	}

//	@GetMapping("/{id}")
//    public ResponseEntity<Object> getStudentById(@PathVariable String id) {
//		Optional<Student> optStudent = studentRepository.findById(id);
//		System.out.println("....................");
//		System.out.println(optStudent);
//		if (optStudent.isPresent()) {
//			Student student = optStudent.get();
//			System.out.println("student " + student);
//			return new ResponseEntity<> (student.toString(),HttpStatus.FOUND);
//		} else {
//			return new ResponseEntity<> (null,HttpStatus.NOT_FOUND);
//		}
//	}

	@PostMapping("/addStudent")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {

		Student createdStudent = studentRepository.save(student);
		return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	}

	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student updatedStudent) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			student.setStudentName(updatedStudent.getStudentName());
			student.setStudentEmail(updatedStudent.getStudentEmail());
			student.setCourses(updatedStudent.getCourses());
			studentRepository.save(student);
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") String id) {
		Optional<Student> optionalStudent = studentRepository.findById(id);
		if (optionalStudent.isPresent()) {
			studentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted Student successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/courses")
	public Collection<Course> getCoursesByStudentId(@PathVariable String id) {
		Optional<Student> optStudent = studentRepository.findById(id);
		if (optStudent.isPresent()) {
			Student student = optStudent.get();
			Collection<Course> courses = student.getCourses();
			return courses;
		} else {
			return null;
		}
	}

	@PostMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<Student> enrollForCourse(@PathVariable String studentId, @PathVariable String courseId) {
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		Optional<Course> optionalCourse = courseRepository.findById(courseId);
		if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
			Student student = optionalStudent.get();
			Course course = optionalCourse.get();
			studentRepository.enrollForCourse(studentId, courseId);
			student.getCourses().add(course);
			studentRepository.save(student);
			
			return new ResponseEntity<>(student,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
