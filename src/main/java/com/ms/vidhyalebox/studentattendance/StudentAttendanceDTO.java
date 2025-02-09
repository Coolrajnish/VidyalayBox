package com.ms.vidhyalebox.studentattendance;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StudentAttendanceDTO extends GenericDTO {

	@NotEmpty(message = "Org unique ID is mandatory")
	private String orgUniqId;

	@NotEmpty(message = "Student ID is mandatory")
	private String studentId;

	@NotEmpty(message = "Class ID is mandatory")
	private String classId;

	@NotEmpty(message = "Date is mandatory")
	private String date;

	private boolean isHoilday;

	@NotEmpty(message = "Status is mandatory")
	private String status; // PRESENT/ABSENT/LATE

	private boolean sendNotificationIfAbsent;

}
