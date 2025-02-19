package com.ms.vidhyalebox.feesamount;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.feestype.FeesTypeEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FeesAmountDTO extends GenericDTO {

	private String orgUniqId;

	private Long feeTypeId;

	private String feeTypes; // COMPULSORY OR OPTIONAL

	private String feesAmount;
}
