package com.ms.vidhyalebox.feesamount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.vidhyalebox.fees.FeesEntity;
import com.ms.vidhyalebox.feestype.FeesTypeEntity;
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
@ToString(callSuper = true, exclude = {"fees"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "fees_amount")
public class FeesAmountEntity extends GenericEntity {

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@ManyToOne
	@JoinColumn(name = "feeTypeId", nullable = false)
	private FeesTypeEntity feeType;

	@Column(name = "feeTypes")
	private String feeTypes; // COMPULSORY OR OPTIONAL

	@Column(name = "feesAmount")
	private String feesAmount;

	@ManyToOne
	@JoinColumn(name = "feesId")
	@JsonIgnore
	private FeesEntity fees;
}
