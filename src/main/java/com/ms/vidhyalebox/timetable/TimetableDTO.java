package com.ms.vidhyalebox.timetable;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.subject.SubjectEntity;
import com.ms.vidhyalebox.teacher.TeacherEntity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TimetableDTO extends GenericDTO {

	@NotEmpty(message = "Org unique ID is mandatory")
    private String school;

	@NotEmpty(message = "Teacher ID is mandatory")
    private Long teacher;

	@NotEmpty(message = "Class ID is mandatory")
    private Long classEntity;
   
	@NotEmpty(message = "Subject ID is mandatory")
    private Long subject;
    
	@NotEmpty(message = "Date is mandatory")
    private String date;
    
	@NotEmpty(message = "Day is mandatory")
    private String day; // e.g., Monday, Tuesday
    
	@NotEmpty(message = "Start time is mandatory")
    private String startTime;
    
	@NotEmpty(message = "End time is mandatory")
    private String endTime;

}
