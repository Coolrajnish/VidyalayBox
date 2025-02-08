package com.ms.vidhyalebox.stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface StreamRepo extends GenericRepo<StreamEntity, Long> {
	 @Query(value = "SELECT * FROM stream s WHERE s.org_uniq_id =:orgId  AND LOWER(s.stream_name) LIKE LOWER(CONCAT('%', :searchText, '%'))", nativeQuery = true)
	    public Page<StreamEntity> search(@Param("orgId") String orgId,
	                                           @Param("searchText") String searchText,
	                                           Pageable pageable);
}
