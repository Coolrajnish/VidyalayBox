package com.ms.shared.api.auth.mediumDTO;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

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
