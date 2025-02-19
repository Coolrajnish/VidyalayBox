package com.ms.vidhyalebox.section;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SectionDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Section name is mandatory")
    private String sectionName;

  //  private ClassDTO classDTO;

    private boolean isActive = true;
}
