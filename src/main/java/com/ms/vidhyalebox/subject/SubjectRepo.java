package com.ms.vidhyalebox.subject;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface SubjectRepo extends GenericRepo<SubjectEntity, Long> {
	@Query(value = "SELECT * FROM subject s WHERE s.org_uniq_id =:orgId  AND (s.subject_name IS NULL OR LOWER(s.subject_name) LIKE LOWER(CONCAT('%', :searchText, '%')))", nativeQuery = true)
	public Page<SubjectEntity> search(@Param("orgId") String orgId, @Param("searchText") String searchText,
			Pageable pageable);

	public Optional<SubjectEntity> findBySubjectCode(String object);
}
