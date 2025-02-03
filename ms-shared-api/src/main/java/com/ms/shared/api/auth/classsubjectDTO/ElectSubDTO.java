package com.ms.shared.api.auth.classsubjectDTO;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

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
