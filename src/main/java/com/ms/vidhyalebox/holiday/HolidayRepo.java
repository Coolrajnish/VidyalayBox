package com.ms.vidhyalebox.holiday;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface HolidayRepo extends GenericRepo<HolidayEntity, Long> {
	 @Query(value = "SELECT * FROM espense s WHERE s.org_uniq_id =:orgId  AND LOWER(s.title) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(s.descr) LIKE LOWER(CONCAT('%', :searchText, '%'))", nativeQuery = true)
	    public Page<HolidayEntity> search(@Param("orgId") String orgId,
	                                           @Param("searchText") String searchText,
	                                           Pageable pageable);
}
