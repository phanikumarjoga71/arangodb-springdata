package com.demo.arangodbtest.repository;

import org.springframework.stereotype.Repository;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.demo.arangodbtest.model.EnrolledIn;
import com.demo.arangodbtest.model.Student;
import com.demo.arangodbtest.queries.StudentQueries;

@Repository
public interface StudentRepository extends ArangoRepository<Student, String> {
	
//	public Iterable<Student> findByStudentName(String studentName);
//	
//	@Query(StudentQueries.findAllStudents)
//	public Iterable<BaseDocument> getAllStudents();
//	
//	@Query(StudentQueries.findStudentById)
//	public BaseDocument findStudentById(String id);
//	
	@Query(StudentQueries.enrollForCourse)
	public EnrolledIn enrollForCourse(String studentId, String courseId);
}
