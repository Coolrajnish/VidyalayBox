package com.ms.vidhyalebox.feestype;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FeesTypeDTO extends GenericDTO {

	@NotEmpty(message = "Org unique Id is mandatory")
	private String orgUniqId;

	private String feesName;

	private String feesDescr;

}
