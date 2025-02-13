package com.ms.vidhyalebox.teacher;

import java.util.ArrayList;
import java.util.List;

import com.ms.vidhyalebox.assignment.AssignmentEntity;
import com.ms.vidhyalebox.leavesettings.LeaveSettingsEntity;
//import com.ms.shared.util.util.domain.GenericEntity;
//import com.ms.vidhyalebox.role.RoleEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.salary.SalaryEntity;
import com.ms.vidhyalebox.user.UserEntity;
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
@Table(name = "teacher")
public class TeacherEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "totalMonthLeave", nullable = false)
	private LeaveSettingsEntity leavesettings;

	@OneToOne
	@JoinColumn(name = "salaryId")
	private SalaryEntity salary;

	@Column(name = "currentAddr")
	private String currentAddr;

	@Column(name = "permanentAddr")
	private String permanentAddr;

	@Column(name = "qualification")
	private String qualification;

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<AssignmentEntity> assignments = new ArrayList<>();
}
