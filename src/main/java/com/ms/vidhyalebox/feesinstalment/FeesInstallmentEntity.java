package com.ms.vidhyalebox.feesinstalment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.vidhyalebox.fees.FeesEntity;
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
@ToString(callSuper = true, exclude = {"feesInstallment"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "fees_installment")
public class FeesInstallmentEntity extends GenericEntity {

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@Column(name = "fees_installment_name")
	private String feeInstallmentName;

	@Column(name = "feeInstallmentDueDate")
	private String feeInstallmentDueDate;
	
	@Column(name = "feeDueChargesType")
	private String feeDueChargesType;

	@Column(name = "feeDueCharges")
	private String feeDueCharges;
	
	@ManyToOne
	@JoinColumn(name = "fee_id")
	@JsonIgnore
	private FeesEntity feesInstallment;
	
}
