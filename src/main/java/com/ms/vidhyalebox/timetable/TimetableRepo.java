package com.ms.vidhyalebox.timetable;

import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface TimetableRepo extends GenericRepo<TimetableEntity, Long> {
//	 @Query(value = "SELECT * FROM timetable t WHERE u.org_uniq_id =:orgId  AND OR LOWER(u.first_name) LIKE LOWER(CONCAT( :searchText, '%')) OR LOWER(u.last_name) LIKE LOWER(CONCAT( :searchText, '%')) OR LOWER(u.identity_provider) LIKE LOWER(CONCAT( :searchText, '%')) OR LOWER(s.admission_number) LIKE LOWER(CONCAT( :searchText, '%'))", nativeQuery = true)
//	    public Page<TimetableEntity> search(@Param("orgId") String orgId,
//	                                           @Param("searchText") String searchText,
//	                                           Pageable pageable);
}
