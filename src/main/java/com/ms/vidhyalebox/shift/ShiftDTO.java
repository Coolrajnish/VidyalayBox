package com.ms.vidhyalebox.shift;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    private boolean isActive = true;
}
