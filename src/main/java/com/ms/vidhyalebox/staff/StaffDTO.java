package com.ms.vidhyalebox.staff;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.payrollSettings.PayrollEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StaffDTO extends GenericDTO {

	@NotEmpty(message = "Org unique Id is mandatory")
	private String school;

	@NotEmpty(message = "First name is mandatory")
	private String firstName;

	@NotEmpty(message = "Last name is mandatory")
	private String lastName;

	@NotEmpty(message = "Phone number is mandatory")
	private String phoneNo;

	@NotEmpty(message = "Email ID is mandatory")
	private String email;

	@NotEmpty(message = "DOB is mandatory")
	private String dob;

	@NotEmpty(message = "First name is mandatory")
	private String joiningDate;

	@NotEmpty(message = "Salary is mandatory")
	private String salary;
	
	@NotEmpty(message = "IsActive is mandatory")
	private boolean isActive;

	private String identity;

	private List<Long> payroll;
	
	

}
