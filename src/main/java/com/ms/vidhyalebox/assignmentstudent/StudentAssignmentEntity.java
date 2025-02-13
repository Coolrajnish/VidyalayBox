package com.ms.vidhyalebox.assignmentstudent;

import com.ms.vidhyalebox.assignment.AssignmentEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.student.StudentEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true) 
@Entity
@Table(name = "student_assignment")
public class StudentAssignmentEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@ManyToOne
	@JoinColumn(name = "assignment_id", nullable = false)
	private AssignmentEntity assignment;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private StudentEntity student;

	private String status = "PENDING"; // PENDING, SUBMITTED, GRADED

	private Integer points;

	@Column(columnDefinition = "TEXT")
	private String feedback;

	private String submittedDate;

	private String fileUrl;

}
