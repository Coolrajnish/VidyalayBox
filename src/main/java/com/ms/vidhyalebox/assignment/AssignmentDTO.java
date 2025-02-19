package com.ms.vidhyalebox.assignment;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AssignmentDTO extends GenericDTO {

	@NotEmpty(message = "Org unique ID is mandatory")
	private String orgUniqId;

	@NotEmpty(message = "Assignment name is mandatory")
	private String assignmentName;

	@NotEmpty(message = "Class ID is mandatory")
	private Long classId;

	@NotEmpty(message = "Subject ID is mandatory")
	private Long subjectId;

	private Long createdById;

	// private Long editTeacherId;

	private String dueDate;

	@NotEmpty(message = "Session ID is mandatory")
	private Long session;

	private String description;

	private String resubmissionDays;

	private String points;

	private String fileUrl;

	private List<MultipartFile> files = new ArrayList<>();

}
