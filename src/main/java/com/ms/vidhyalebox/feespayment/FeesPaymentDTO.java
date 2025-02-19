package com.ms.vidhyalebox.feespayment;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.feestype.FeesTypeEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FeesPaymentDTO extends GenericDTO {

	@NotEmpty(message = "Org unique Id is mandatory")
	private String orgUniqId;

	private FeesTypeEntity feeType;

	private String feeTypes; // COMPULSORY OR OPTIONAL

	private String feesAmount;

}
