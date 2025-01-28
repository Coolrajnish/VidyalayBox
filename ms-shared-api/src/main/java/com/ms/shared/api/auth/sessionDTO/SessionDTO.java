package com.ms.shared.api.auth.sessionDTO;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

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
