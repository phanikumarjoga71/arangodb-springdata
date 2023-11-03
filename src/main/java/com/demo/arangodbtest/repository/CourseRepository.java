package com.demo.arangodbtest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.arangodb.entity.BaseDocument;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.demo.arangodbtest.model.Course;
import com.demo.arangodbtest.queries.CourseQueries;

@Repository
public interface CourseRepository extends ArangoRepository<Course, String> {
	
//	@Query(CourseQueries.findAllCourses)
//	public List<BaseDocument> getAllCourses();
//	
//	@Query(CourseQueries.findCourseById)
//	public BaseDocument findCourseById(String id);
//	
	@Query(CourseQueries.findCourseByTechnology)
	public List<BaseDocument> findCourseByTechnology(String technology);
	
//	public List<Course> findByTechnology(String technology);
}

