package com.ms.vidhyalebox.session;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.vidhyalebox.util.repo.GenericRepo;

public interface SessionRepo extends GenericRepo<SessionEntity, Long> {
    @Query(value = "SELECT * FROM session s WHERE s.org_uniq_id =:orgId  AND (s.session_name IS NULL OR LOWER(s.session_name) LIKE LOWER(CONCAT('%', :searchText, '%')))", nativeQuery = true)
    public Page<SessionEntity> search(@Param("orgId") String orgId,
                                      @Param("searchText") String searchText,
                                      Pageable pageable);
}
