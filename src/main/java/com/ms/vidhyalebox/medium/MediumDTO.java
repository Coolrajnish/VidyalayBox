package com.ms.vidhyalebox.medium;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MediumDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqueId;

    @NotEmpty(message = "Medium name is mandatory")
    private String mediumName;

    private boolean isActive = true;

}
