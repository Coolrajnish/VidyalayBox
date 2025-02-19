package com.ms.vidhyalebox.feesdiscount;

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
@Table(name = "fees_discount")
public class FeesDiscountEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@Column(name = "feeDiscountName")
	private String feeDiscountName;

	@Column(name = "feeDiscountDescr")
	private String feeDiscountDescr;
	
	@Column(name = "feeDiscountType")
	private String feeDiscountType;

	@Column(name = "feeDiscountCharges")
	private String feeDiscountCharges;
	
	
}
