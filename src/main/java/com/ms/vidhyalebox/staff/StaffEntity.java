package com.ms.vidhyalebox.staff;

import com.ms.vidhyalebox.leavesettings.LeaveSettingsEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.salary.SalaryEntity;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "staff")
public class StaffEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserEntity user;

	@OneToOne
	@JoinColumn(name = "totalMonthLeave", nullable = false)
	private LeaveSettingsEntity leavesettings;
	
	@OneToOne
	@JoinColumn(name = "salaryId")
    private SalaryEntity salary;

}
