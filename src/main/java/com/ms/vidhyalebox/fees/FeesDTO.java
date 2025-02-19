package com.ms.vidhyalebox.fees;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.feesamount.FeesAmountDTO;
import com.ms.vidhyalebox.feesinstalment.FeesInstallmentDTO;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FeesDTO extends GenericDTO {

	@NotEmpty(message = "Org unique Id is mandatory")
	private String orgUniqId;

	private String feeName;

	private String feeDueDate;
	
	private String feeDueChargesType;

	private String feeDueCharges;
	
	private Long sessionId;

	private List<FeesAmountDTO> feesAmunt;
	
	private List<Long> classId;
	
	private List<FeesInstallmentDTO> feesInstallment;
}
