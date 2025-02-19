package com.ms.vidhyalebox.leavesettings;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.ResourceNotFoundException;

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
	
	public LeaveSettingsEResp getLeaveSettings(String orgUniqId) {
	    // Retrieve the result list (we expect only one result)
	    List<Object[]> resultList = leaveRepo.findLeaveSettingsByOrgUniqId(orgUniqId);

	    // If no result is found, throw an exception
	    if (resultList.isEmpty()) {
	        throw new ResourceNotFoundException("No leave settings found for orgUniqId: " + orgUniqId);
	    }

	    // Retrieve the first result from the list
	    Object[] result = resultList.get(0);

	    // Safely cast each element to the appropriate type, ensuring no ClassCastException
	    Long id = (Long) result[0];
	    String totalLeavePerMnth = (String) result[1]; // Assuming the second element is a String
	    String holiday = (String) result[2]; // Assuming the third element is a String
	    Long sessionId = (result[3] instanceof Number) ? ((Number) result[3]).longValue() : null;

	    // Create and return the DTO
	    return new LeaveSettingsEResp(id, totalLeavePerMnth, holiday, sessionId);
	}

}
