package com.ms.vidhyalebox.leave;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class LeaveserviceImpl extends GenericService<GenericEntity, Long> implements LeaveService {

	private final LeaveRepo leaveRepo;
	private final LeaveMapperNormal leaveMapperNormal;

	public LeaveserviceImpl(LeaveRepo leaveRepo, LeaveMapperNormal leaveMapperNormal) {
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
