package com.ms.vidhyalebox.leavesettings;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.vidhyalebox.util.repo.GenericRepo;

@Repository
public interface LeaveSettingsRepo extends GenericRepo<LeaveSettingsEntity, Long> {
	@Query(value = "select * from leavesettings l where l.org_uniq_id = ?1", nativeQuery = true )
	 Optional<List<LeaveSettingsEntity>> getLeaveSettings(String orgId);
}
