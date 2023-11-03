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
import com.arangodb.entity.BaseDocument;
import com.demo.arangodbtest.model.Course;
import com.demo.arangodbtest.repository.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
    private CourseRepository courseRepository;
	
	@GetMapping
	public List<Course> getAllCourse(){
		List<Course> courses=new ArrayList<Course>() ;  
		courseRepository.findAll().forEach  (courses::add); 
	    System.out.println(courses.toArray().toString());
	      return courses;
	}
	
//	@GetMapping
//	public ResponseEntity<List<BaseDocument>> getAllCourses(){
//		List<BaseDocument> courses = courseRepository.getAllCourses();
//		if (courses.iterator().hasNext()) {
//			return new ResponseEntity<>(courses,HttpStatus.FOUND);
//		}
//		else {
//			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//		}
//	}
	
	@GetMapping("/{id}")
	public Course getCourseById(@PathVariable String id){
		Optional<Course> optCourse = courseRepository.findById(id);
		if (optCourse.isPresent()) {
			Course course = optCourse.get();
			System.out.println("course " + course);
			return course;
		} else {
			return null;
		}
	}
	
//	@GetMapping("/{id}")
//    public ResponseEntity<BaseDocument> getCourseById(@PathVariable String id) {
//        BaseDocument course = courseRepository.findCourseById(id);
//        if (course != null) {
//			return new ResponseEntity<>(course,HttpStatus.FOUND);
//		}
//		else {
//			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//		}
//    }
	
	@GetMapping("/byTechnology/{technology}")
	public ResponseEntity<List<BaseDocument>> getCourseByTechnology(@PathVariable String technology){
		List<BaseDocument> course = courseRepository.findCourseByTechnology(technology);
		if (course != null) {
			return new ResponseEntity<>(course,HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addCourse")
    public ResponseEntity<Course> createCourseRegistration(@RequestBody Course course) {
		Course createdCourse = courseRepository.save(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }
	
	@PutMapping("/updateCourse/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") String id, @RequestBody Course updatedCourse){
		Optional<Course> optionalCourse = courseRepository.findById(id);
		if (optionalCourse.isPresent()) {
			Course course = optionalCourse.get();
			course.setCourseName(updatedCourse.getCourseName());
			course.setTechnology(updatedCourse.getTechnology());
			courseRepository.save(course);
	    	return new ResponseEntity<>(updatedCourse,HttpStatus.OK);
    	}
		else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable("id") String id){
		Optional<Course> optionalCourse = courseRepository.findById(id);
		if (optionalCourse.isPresent()) {
			courseRepository.deleteById(id);
			return new ResponseEntity<> ("Deleted Course successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> ("Course not found",HttpStatus.NOT_FOUND);
		}
	}

}
