package com.ms.vidhyalebox.Class;

import com.ms.vidhyalebox.medium.MediumEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.section.SectionEntity;
import com.ms.vidhyalebox.shift.ShiftEntity;
import com.ms.vidhyalebox.stream.StreamEntity;
import com.ms.vidhyalebox.teacher.TeacherEntity;
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
@Table(name = "class")
public class ClassEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@Column(name = "class_name")
	private String className;

	@ManyToOne
	@JoinColumn(name = "medium_id")
	private MediumEntity medium;

	@ManyToOne
	@JoinColumn(name = "class_teacher_id")
	private TeacherEntity classTeacher;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private SectionEntity sectionEntity;

	@ManyToOne
	@JoinColumn(name = "stream_id")
	private StreamEntity streamEntity;

	@ManyToOne
	@JoinColumn(name = "shift_id")
	private ShiftEntity shiftEntity;

	@Column(name = "is_active")
	private boolean isActive = true;
}
