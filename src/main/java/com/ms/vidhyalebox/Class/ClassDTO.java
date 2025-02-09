package com.ms.vidhyalebox.Class;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ClassDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqueId;

    @NotEmpty(message = "Class name is mandatory")
    private String className;

    private boolean isActive = true;

    @NotEmpty(message = "Medium Id is mandatory")
    private Long mediumId;

    @NotEmpty(message = "Shift Id is mandatory")
    private Long shiftId;

    @NotEmpty(message = "Section Id is mandatory")
    private Long sectionId;

    @NotEmpty(message = "Stream Id is mandatory")
    private Long streamId;

}
