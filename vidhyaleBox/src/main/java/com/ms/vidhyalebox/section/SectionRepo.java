package com.ms.vidhyalebox.section;

import com.ms.shared.util.util.repo.GenericRepo;
import com.ms.vidhyalebox.medium.MediumEntity;
import com.ms.vidhyalebox.session.SessionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepo extends GenericRepo<SectionEntity, Long> {

    @Query(value = "SELECT * FROM section p WHERE p.org_uniq_id =:orgId  AND LOWER(p.section_name) LIKE LOWER(CONCAT('%', :searchText, '%'))", nativeQuery = true)
    public Page<SectionEntity> search(@Param("orgId") String orgId,
                                      @Param("searchText") String searchText,
                                      Pageable pageable);
}
