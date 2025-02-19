package com.ms.vidhyalebox.feestype;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "fees_type")
public class FeesTypeEntity extends GenericEntity {

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@Column(name = "fees_name")
	private String feeName;

	@Column(name = "fees_descr")
	private String feeDescr;
	

}
