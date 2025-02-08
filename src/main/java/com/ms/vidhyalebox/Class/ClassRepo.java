package com.ms.vidhyalebox.Class;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface ClassRepo extends GenericRepo<ClassEntity, Long> {

	
    public Page<ClassEntity> search(@Param("orgId") String orgId,
            @Param("searchText") String searchText,
            Pageable pageable);
	
	
}
