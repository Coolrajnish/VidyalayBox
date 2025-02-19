package com.ms.vidhyalebox.feespayment;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.fees.FeesEntity;
import com.ms.vidhyalebox.feestype.FeesTypeEntity;
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
@Table(name = "fees_payment")
public class FeesPaymentEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@ManyToOne
	@JoinColumn(name = "studentId", nullable = false)
	private StudentEntity student;

	@ManyToOne
	@JoinColumn(name = "classId", nullable = false)
	private ClassEntity classId;

	@ManyToOne
	@JoinColumn(name = "feesId", nullable = false)
	private FeesEntity fees;

	@Column(name = "amountPaid")
	private Double amountPaid;

	@Column(name = "paymentDate")
	private String paymentDate;

	private String transactionId; // For online payments

	@Column(name = "paymentMethod")
	private String paymentMethod; // CASH, CARD, BANK_TRANSFER

	private Boolean isLatePayment = false; // Whether the payment was late

	private Double latePaymentCharge; // Applied if late payment

	private Boolean isInstalmentPayment = false; // If this payment is an instalment

	private String paymentStatus;
	
}
