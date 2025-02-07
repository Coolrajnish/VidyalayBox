package com.ms.vidhyalebox.sharedapi.sessionDTO;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SessionDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqueId;

    @NotEmpty(message = "Session name is mandatory")
    private String sessionName;

    @NotEmpty(message = "Session start date is mandatory")
    private String startDate;

    @NotEmpty(message = "Session end date is mandatory")
    private String endDate;

    private boolean isActive = true;

}
