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
public class ClassSubjectDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Subject name is mandatory")
    private String className;

    @NotEmpty(message = "Core subject is mandatory")
    private List<Long> coreSubject;

    @NotEmpty(message = "Elective subject is mandatory")
    private ElectSubDTO electSubject;

    @NotEmpty(message = "Subject type is mandatory")
    private String subjectType;

}
