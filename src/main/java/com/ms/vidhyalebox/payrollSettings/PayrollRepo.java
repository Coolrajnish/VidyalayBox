package com.ms.vidhyalebox.payrollSettings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface PayrollRepo extends GenericRepo<PayrollEntity, Long> {
	@Query(value = "SELECT * FROM payroll p WHERE p.org_uniq_id =:orgId  AND (p.payroll_name IS NULL OR LOWER(p.payroll_name) LIKE LOWER(CONCAT('%', :searchText, '%'))) OR (p.payroll_type is NULL OR LOWER(p.payroll_type) LIKE LOWER(CONCAT('%', :searchText, '%')))", nativeQuery = true)
	public Page<PayrollEntity> search(@Param("orgId") String orgId, @Param("searchText") String searchText,
			Pageable pageable);
}
