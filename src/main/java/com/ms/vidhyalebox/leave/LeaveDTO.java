package com.ms.vidhyalebox.leave;


import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LeaveDTO extends GenericDTO {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Organization ID is mandatory")
    private String orgId;
    
	@NotEmpty(message = "User ID is mandatory")
	private final String userId;


	@NotEmpty(message = "leaveStrtDate is mandatory")
    private String leaveStrtDate;
    
	@NotEmpty(message = "leaveEndDate is mandatory")
    private String leaveStrtEnd;
    
	@NotEmpty(message = "Leave reason is mandatory")
    private String reason;
    
    private String status = "PENDING"; 
	
}
