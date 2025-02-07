package com.ms.vidhyalebox.leavesettings;


import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LeaveSettingsDTO extends GenericDTO {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Organization ID is mandatory")
	private final String orgUniqueId;

	@NotEmpty(message = "Total leave is mandatory")
	private final String totalLeavePerMnth;

	@NotEmpty(message = "Holiday is mandatory")
	private final String holiday;

	@NotEmpty(message = "SessionId is mandatory")
	private final String sessionId;

}
