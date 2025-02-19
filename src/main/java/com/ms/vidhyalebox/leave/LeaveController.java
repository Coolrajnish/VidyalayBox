package com.ms.vidhyalebox.leave;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;


@RestController
@Validated
@RequestMapping("/leave")
public class LeaveController extends GenericController<LeaveDTO, Long> {

	private final LeaveserviceImpl leaveService;

	public LeaveController(LeaveserviceImpl leaveService) {
        this.leaveService = leaveService;
    }

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		return leaveService;
	}
}
