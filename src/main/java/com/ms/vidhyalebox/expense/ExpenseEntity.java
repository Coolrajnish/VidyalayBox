package com.ms.vidhyalebox.expense;

import com.ms.vidhyalebox.expensecategory.ExpenseCategoryEntity;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.session.SessionEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.Column;
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
@Table(name = "expense")
public class ExpenseEntity extends GenericEntity {

    @ManyToOne
    @JoinColumn(name = "orgUniqId", nullable = false)
    private OrgClientEntity school;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private String amount;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "descr")
    private String descr;
    
    @ManyToOne
    @JoinColumn(name = "expense_category_id", nullable = false)
    private ExpenseCategoryEntity expensecategory;
     
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;
    
}
