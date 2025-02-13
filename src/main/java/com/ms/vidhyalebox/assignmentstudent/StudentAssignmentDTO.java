package com.ms.vidhyalebox.assignmentstudent;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StudentAssignmentDTO extends GenericDTO {

	@NotEmpty(message = "Org unique ID is mandatory")
	private String orgUniqId;

	@NotEmpty(message = "Assignment name is mandatory")
	private String assignment;

	@NotEmpty(message = "Student name is mandatory")
	private String student;

	private String status = "PENDING"; // PENDING, SUBMITTED, GRADED

	private Integer points;

	private String feedback;

	private String submittedDate;

	private String fileUrl;

}
