package com.ms.vidhyalebox.expensecategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface ExpenseCategoryRepo extends GenericRepo<ExpenseCategoryEntity, Long> {
	 @Query(value = "SELECT * FROM espense_category s WHERE s.org_uniq_id =:orgId  AND LOWER(s.expense_name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(s.expense_descr) LIKE LOWER(CONCAT('%', :searchText, '%'))", nativeQuery = true)
	    public Page<ExpenseCategoryEntity> search(@Param("orgId") String orgId,
	                                           @Param("searchText") String searchText,
	                                           Pageable pageable);
}
