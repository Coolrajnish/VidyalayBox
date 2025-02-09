package com.ms.vidhyalebox.classsubject;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ElectSubDTO extends GenericDTO {



    @NotEmpty(message = "Elective subject is mandatory")
    private List<Long> electSubject;

    @NotEmpty(message = "Total elective count is mandatory")
    private String totalElectCount;

    @NotEmpty(message = "Group is mandatory")
    private int group;

}
