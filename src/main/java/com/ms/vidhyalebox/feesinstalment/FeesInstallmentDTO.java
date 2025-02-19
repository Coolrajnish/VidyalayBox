package com.ms.vidhyalebox.feesinstalment;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FeesInstallmentDTO extends GenericDTO {

	private String orgUniqId;

	private String feeInstallmentName;

	private String feeInstallmentDueDate;
	
	private String feeDueChargesType;

	private String feeDueCharges;

}
