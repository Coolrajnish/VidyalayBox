package com.ms.vidhyalebox.leavesettings;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.stream.StreamDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/leavesettings")
public class LeaveSettingsController extends GenericController<LeaveSettingsDTO, Long> {

	private final LeaveSettingsserviceImpl leaveService;

	public LeaveSettingsController(LeaveSettingsserviceImpl leaveService) {
        this.leaveService = leaveService;
    }

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return leaveService;
	}
}
