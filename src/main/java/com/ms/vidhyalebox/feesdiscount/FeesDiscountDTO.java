package com.ms.vidhyalebox.feesdiscount;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FeesDiscountDTO extends GenericDTO {

	@NotEmpty(message = "Org unique Id is mandatory")
	private String orgUniqId;

	private String feeDiscountName;

	private String feeDiscountDescr;
	
	private String feeDiscountType;

	private String feeDiscountCharges;
	

}
