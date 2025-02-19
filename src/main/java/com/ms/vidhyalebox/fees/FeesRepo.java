package com.ms.vidhyalebox.fees;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface FeesRepo extends GenericRepo<FeesEntity, Long> {

	@Query(value = "SELECT * FROM vidhyalebox.fees f "
			+ "WHERE f.org_Uniq_Id = :orgId AND " 
			+ "(f.fees_name IS NULL OR LOWER(f.fees_name) LIKE LOWER(CONCAT( :serachText,'%'))) ", nativeQuery = true)
	public Page<FeesEntity> search(@Param("orgId") String orgId, @Param("serachText") String serachText,
			Pageable pageable);

}
