package com.ms.vidhyalebox.subject;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SubjectDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Subject name is mandatory")
    private String subjectName;

    @NotEmpty(message = "Subject code is mandatory")
    private String subjectCode;

    @NotEmpty(message = "Medium ID is mandatory")
    private Long mediumId;

    @NotEmpty(message = "Subject type is mandatory")
    private String subjectType;

    private boolean isActive;

}
