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
public class SubjectTeacherDTO extends GenericDTO {

    @NotEmpty(message = "Org unique Id is mandatory")
    private Long orgUniqId;

    @NotEmpty(message = "Class Id is mandatory")
    private Long classId;

    @NotEmpty(message = "Class teacher Id is mandatory")
    private Long teacherId;
    
    //One subject can have multiple teacher
    @NotEmpty(message = "Teacher ID list is mandatory")
    private List<Long> teacher;

    @NotEmpty(message = "Subject ID is mandatory")
    private Long subject;

}
