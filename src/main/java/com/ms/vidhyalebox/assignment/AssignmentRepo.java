package com.ms.vidhyalebox.assignment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface AssignmentRepo extends GenericRepo<AssignmentEntity, Long> {
	 @Query(value = "SELECT * FROM assignment s WHERE s.org_uniq_id =:orgId  AND (s.assignment_name IS NULL OR LOWER(s.assignment_name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR s.descr IS NULL OR LOWER(s.descr) LIKE LOWER(CONCAT('%', :searchText, '%')))", nativeQuery = true)
	    public Page<AssignmentEntity> search(@Param("orgId") String orgId,
	                                           @Param("searchText") String searchText,
	                                           Pageable pageable);
}
