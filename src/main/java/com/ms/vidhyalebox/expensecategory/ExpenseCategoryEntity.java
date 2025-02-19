package com.ms.vidhyalebox.expensecategory;

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
@Table(name = "expense_category")
public class ExpenseCategoryEntity extends GenericEntity {
	
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "expense_name")
    private String expenseName;

    @Column(name = "expense_descr")
    private String expenseDescr;
    
}
