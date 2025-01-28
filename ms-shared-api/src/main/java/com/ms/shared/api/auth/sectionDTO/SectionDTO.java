package com.ms.shared.api.auth.sectionDTO;

import com.ms.shared.api.auth.ClassDTO.ClassDTO;
import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SectionDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Section name is mandatory")
    private String sectionName;

  //  private ClassDTO classDTO;

    private boolean isActive;
}
