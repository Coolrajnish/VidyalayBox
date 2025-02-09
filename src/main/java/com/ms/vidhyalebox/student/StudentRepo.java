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
    
    //Make pojo corresponding to variable returns from this
    @Query(value = "SELECT u.first_name, u.last_name,u.role, u.gender, u.mobile_number,u.birth_date, p.admission_date,c.class_name, sc.section_name FROM student p JOIN users u on u.id = p.user_id JOIN parent sp on sp.id = p.parent_id JOIN class c on c.id = p.class_id JOIN section sc on sc.id = c.section_id WHERE u.org_uniq_id =:orgId  AND LOWER(u.first_name) LIKE LOWER(CONCAT(:searchText, '%') OR LOWER(u.last_name) LIKE LOWER(CONCAT( :searchText, '%') OR LOWER(u.email) LIKE LOWER(CONCAT( '%',:searchText, '%')) OR LOWER(u.mobile_number) LIKE LOWER(CONCAT( :searchText, '%'))", nativeQuery = true)
    public Page<StudentEntity> search(@Param("orgId") String orgId,
                                           @Param("searchText") String searchText,
                                           Pageable pageable);
}
