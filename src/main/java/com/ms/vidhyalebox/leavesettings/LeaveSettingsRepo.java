package com.ms.vidhyalebox.leavesettings;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface LeaveSettingsRepo extends GenericRepo<LeaveSettingsEntity, Long> {
	@Query(value = "SELECT l.id, l.total_leave_per_mnth, l.holiday, s.id as session_id "
			+ "FROM leavesettings l " + "JOIN session s ON s.id = l.session_id "
			+ "WHERE l.org_uniq_id = ?1 AND s.is_active IS TRUE", nativeQuery = true)
	List<Object[]> findLeaveSettingsByOrgUniqId(@Param("orgUniqId") String orgUniqId);
}
