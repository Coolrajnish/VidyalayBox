package com.ms.vidhyalebox.assignment;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.assignmentstudent.StudentAssignmentEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.session.SessionEntity;
import com.ms.vidhyalebox.subject.SubjectEntity;
import com.ms.vidhyalebox.teacher.TeacherEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "assignment")
public class AssignmentEntity extends GenericEntity {

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@Column(name = "assignmentName", nullable = false)
	private String assignmentName;

	@ManyToOne
	@JoinColumn(name = "classId", nullable = false)
	private ClassEntity className;

	@ManyToOne
	@JoinColumn(name = "subjectId", nullable = false)
	private SubjectEntity subject;

	@ManyToOne
	@JoinColumn(name = "teacherId", nullable = false)
	private TeacherEntity teacher;

	@Column(name = "dueDate", nullable = false)
	private String dueDate;

	@Column(name = "descr", nullable = false)
	private String description;

	@OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
	private List<StudentAssignmentEntity> submissions = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "session_id", nullable = false)
	private SessionEntity session;

	@Column(name = "resubmissionDays", nullable = false)
	private String resubmissionDays;

	@Column(name = "points", nullable = false)
	private String points;

	private String fileUrl;

	private String image;

}
