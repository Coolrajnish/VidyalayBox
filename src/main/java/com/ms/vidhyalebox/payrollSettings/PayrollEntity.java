package com.ms.vidhyalebox.payrollSettings;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
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
@Table(name = "payroll")
public class PayrollEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@Column(name = "payroll_name")
	private String payrollName;

	@Column(name = "payroll_type")
	private String payrollType; 

	@Column(name = "amount")
	private String amount;

	@Column(name = "percentage")
	private String percentage;
	
	
}
