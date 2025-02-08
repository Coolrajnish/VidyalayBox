package com.ms.vidhyalebox.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface StudentRepo extends GenericRepo<StudentEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE student SET class_id = :classId WHERE id = :studId and org_uniq_id = :orgId", nativeQuery = true)
    public int transferStudent(Long orgId, Long studId, Long classId);
    
    public Page<StudentEntity> search(@Param("orgId") String orgId,
    		                           @Param("searcText") String searchText,
    		                            Pageable pageable);
}
