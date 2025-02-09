package com.ms.vidhyalebox.payrollSettings;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PayrollDTOs extends GenericDTO {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Organization Id is mandatory")
	private final String orgUniqueId;
	//ALLOWANCE/DEDUCTION
	@NotEmpty(message = "Payroll Type is mandatory")
	private final String payrollType;

	@NotEmpty(message = "Payroll Name is mandatory")
	private final String payrollName;

	private final String amount;

	private final String percentage;

}
