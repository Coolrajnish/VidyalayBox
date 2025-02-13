package com.ms.vidhyalebox.assignmentstudent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface StudentAssignmentRepo extends GenericRepo<StudentAssignmentEntity, Long> {
	@Query(value = "SELECT * FROM student_assignment s WHERE s.org_uniq_id =:orgId  AND LOWER(s.status) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(s.feedback) LIKE LOWER(CONCAT('%', :searchText, '%'))", nativeQuery = true)
	public Page<StudentAssignmentEntity> search(@Param("orgId") String orgId, @Param("searchText") String searchText,
			Pageable pageable);
}
