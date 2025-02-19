package com.ms.vidhyalebox.medium;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ms.vidhyalebox.util.repo.GenericRepo;

public interface MediumRepo extends GenericRepo<MediumEntity, Long> {

//    @Query("SELECT p FROM medium p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchText, '%'))")
//    public Page<MediumEntity> searchMedium(
//            @Param("searchText") String searchText,
//            Pageable pageable
//    );

//    @Query(value = "SELECT p FROM medium p WHERE  LOWER(p.medium_name) LIKE LOWER(CONCAT('%', :searchText, '%'))", nativeQuery = true)
//    public Page<MediumEntity> search( @Param("searchText") String searchText,
//                                      Pageable pageable);

    @Query(value = "SELECT * FROM medium p WHERE p.org_uniq_id =:orgId  AND (p.medium_name is NULL OR LOWER(p.medium_name) LIKE LOWER(CONCAT('%', :searchText, '%')))", nativeQuery = true)
    public Page<MediumEntity> search(@Param("orgId") String orgId,
                                           @Param("searchText") String searchText,
                                           Pageable pageable);


}