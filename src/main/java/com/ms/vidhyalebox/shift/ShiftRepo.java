package com.ms.vidhyalebox.shift;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.medium.MediumEntity;
import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface ShiftRepo extends GenericRepo<ShiftEntity, Long> {	
	
	 @Query(value = "SELECT * FROM shift s WHERE s.org_uniq_id =:orgId  AND LOWER(s.shift_name) LIKE LOWER(CONCAT('%', :searchText, '%'))", nativeQuery = true)
	    public Page<ShiftEntity> search(@Param("orgId") String orgId,
	                                           @Param("searchText") String searchText,
	                                           Pageable pageable);
}
