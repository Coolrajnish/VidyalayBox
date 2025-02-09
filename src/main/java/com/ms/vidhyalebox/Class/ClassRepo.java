package com.ms.vidhyalebox.Class;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface ClassRepo extends GenericRepo<ClassEntity, Long> {

	@Query(value = "SELECT c.class_name, sh.shift_name, "
			+ "s.stream_name, m.medium_name,sc.section_name  FROM vidhyalebox.class "
			+ "c JOIN vidhyalebox.medium m ON m.id = c.medium_id JOIN vidhyalebox.stream s "
			+ "ON s.id = c.stream_id JOIN vidhyalebox.stream s ON sc.id = c.section_id JOIN "
			+ "vidhyalebox.shift sh ON sh.id = c.shift_id WHERE c.org_uniq_id =:orgId AND "
			+ "(c.class_name IS NULL OR LOWER(c.class_name) LIKE LOWER(CONCAT( :serachText,'%'))) "
			+ "OR (m.medium_name IS NULL OR LOWER(m.medium_name) "
			+ "LIKE LOWER(CONCAT( :serachText,'%'))) OR (s.stream_name IS NULL OR "
			+ "LOWER(s.stream_name) LIKE LOWER(CONCAT( :serachText,'%')));", nativeQuery = true)
    public Page<ClassEntity> search(@Param("orgId") String orgId,
            @Param("serachText") String serachText,
            Pageable pageable);
	
	 
}
 