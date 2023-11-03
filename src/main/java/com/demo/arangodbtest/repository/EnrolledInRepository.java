package com.demo.arangodbtest.repository;

import org.springframework.stereotype.Repository;
import com.arangodb.springframework.repository.ArangoRepository;
import com.demo.arangodbtest.model.EnrolledIn;

@Repository
public interface EnrolledInRepository extends ArangoRepository<EnrolledIn, String> {
	
//	@Query(EnrolledInQueries.findAllEnrollments)
//	public List<BaseEdgeDocument> getAllEnrollments();
//	
//	@Query(EnrolledInQueries.findEnrollmententById)
//	public BaseEdgeDocument findEnrollmentById(String id);
}
