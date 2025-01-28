package com.ms.shared.api.auth.shiftDTO;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ShiftDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Shift name is mandatory")
    private String shiftName;

    @NotEmpty(message = "Start time is mandatory")
    private String startTime;

    @NotEmpty(message = "End time is mandatory")
    private String endTime;

    private boolean isActive;
}
