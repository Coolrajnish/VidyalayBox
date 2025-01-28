package com.ms.shared.api.auth.subject;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

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

    @NotEmpty(message = "Medium is mandatory")
    private String medium;

    @NotEmpty(message = "Subject type is mandatory")
    private String subjectType;

    private boolean isActive;

}
