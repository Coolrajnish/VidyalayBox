package com.ms.shared.api.auth.studentDTO;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StudentTransferDTO extends GenericDTO {


    @NotEmpty(message = "Organization Id is mandatory")
    private Long orgId;

    @NotEmpty(message = "Student Id is mandatory")
    private Long stuId;

    @NotEmpty(message = "Class Id is mandatory")
    private Long classId;

}
