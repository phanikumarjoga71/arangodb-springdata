package com.demo.arangodbtest.controller;

import java.util.ArrayList;
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
import com.demo.arangodbtest.model.EnrolledIn;
import com.demo.arangodbtest.model.Student;
import com.demo.arangodbtest.repository.CourseRepository;
import com.demo.arangodbtest.repository.EnrolledInRepository;
import com.demo.arangodbtest.repository.StudentRepository;

@RestController
@RequestMapping("/relations")
public class RelationController {
	
	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
    private CourseRepository courseRepository;
	
	@Autowired
    private EnrolledInRepository enrolledInRepository;
	
	@GetMapping
	public List<EnrolledIn> getAllEnrollments(){
		List<EnrolledIn> enrollments=new ArrayList<EnrolledIn>() ;
		enrolledInRepository.findAll().forEach  (enrollments::add); 
	    System.out.println(enrollments.toArray().toString());
	      return enrollments;
	}
	
//	@GetMapping
//	public ResponseEntity<List<BaseEdgeDocument>> getAllEnrollments(){
//		List<BaseEdgeDocument> enrollments = enrolledInRepository.getAllEnrollments();
//		if (enrollments.iterator().hasNext()) {
//			return new ResponseEntity<>(enrollments,HttpStatus.FOUND);
//		}
//		else {
//			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping("/{id}")
	public EnrolledIn getEnrollmentById(@PathVariable String id){
		Optional<EnrolledIn> optEnrolledIn = enrolledInRepository.findById(id);
		if (optEnrolledIn.isPresent()) {
			EnrolledIn enrolledIn = optEnrolledIn.get();
			System.out.println("Enrollment " + enrolledIn);
			return enrolledIn;
		} else {
			return null;
		}
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<BaseEdgeDocument> getEnrollmentById(@PathVariable("id") String id){
//		BaseEdgeDocument enrolledIn = enrolledInRepository.findEnrollmentById(id);
//		if (enrolledIn != null) {
//			return new ResponseEntity<>(enrolledIn,HttpStatus.FOUND);
//		}
//		else {
//			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//		}
//	}
	
	@PostMapping("/add")
	public ResponseEntity<EnrolledIn> createCourseEnrollment(@RequestBody EnrolledIn enrolledIn) {
		EnrolledIn enrolled = enrolledInRepository.save(enrolledIn);
	       return new ResponseEntity<>(enrolled, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EnrolledIn> updateCourseEnrollment(@PathVariable("id") String id, @RequestBody EnrolledIn updatedEnrollment){
		Optional<EnrolledIn> optionalEnrolledIn = enrolledInRepository.findById(id);
		if (optionalEnrolledIn.isPresent()) {
			EnrolledIn enrolledIn = optionalEnrolledIn.get();
			enrolledIn.setStudent(updatedEnrollment.getStudent());
	    	enrolledIn.setCourse(updatedEnrollment.getCourse());
	    	enrolledInRepository.save(enrolledIn);
	    	return new ResponseEntity<>(updatedEnrollment,HttpStatus.OK);
    	}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCourseEnrollment(@PathVariable("id") String id){
		Optional<EnrolledIn> optionalEnrolledIn = enrolledInRepository.findById(id);
		if (optionalEnrolledIn.isPresent()) {
			enrolledInRepository.deleteById(id);
			return new ResponseEntity<> ("Deleted Enrollment successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> ("Enrollment not found",HttpStatus.NOT_FOUND);
		}
	}
	
}
