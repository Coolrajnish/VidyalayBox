package com.ms.vidhyalebox.leavesettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class LeaveSettingsserviceImpl extends GenericService<GenericEntity, Long> implements LeaveSettingsService {

	private final LeaveSettingsRepo leaveRepo;
	private final LeaveSettingsMapperNormal leaveMapperNormal;

	public LeaveSettingsserviceImpl(LeaveSettingsRepo leaveRepo, LeaveSettingsMapperNormal leaveMapperNormal) {
        this.leaveRepo = leaveRepo;
        this.leaveMapperNormal = leaveMapperNormal;
    }

	@Override
	public JpaRepository getRepo() {
		return leaveRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return leaveMapperNormal;
	}
}
