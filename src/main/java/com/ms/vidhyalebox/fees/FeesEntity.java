package com.ms.vidhyalebox.fees;

import java.util.List;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.feesamount.FeesAmountEntity;
import com.ms.vidhyalebox.feesinstalment.FeesInstallmentEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.session.SessionEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "fees")
public class FeesEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@OneToMany(mappedBy = "fees", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FeesAmountEntity> feeAmounts;

	@OneToMany(mappedBy = "feesInstallment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FeesInstallmentEntity> feeInstallment;

	@ManyToMany
	@JoinTable(name = "fee_classes", joinColumns = @JoinColumn(name = "fee_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
	private List<ClassEntity> applicableClasses;

	@ManyToOne
	@JoinColumn(name = "session_id", nullable = false)
	private SessionEntity session;

	@Column(name = "feeDueDate")
	private String feeDueDate;

	@Column(name = "feeDueChargesType")
	private String feeDueChargesType;

	@Column(name = "feeDueCharges")
	private String feeDueCharges;

	@Column(name = "totalAmount")
	private String totalAmount;

	@Column(name = "compulsoryAmount")
	private String compulsoryAmount;

	@Column(name = "feesName")
	private String feesName;
}
