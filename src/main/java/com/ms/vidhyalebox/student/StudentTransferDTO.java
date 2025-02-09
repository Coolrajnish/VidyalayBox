package com.ms.vidhyalebox.student;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
