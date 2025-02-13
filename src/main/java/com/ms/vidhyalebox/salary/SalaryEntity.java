package com.ms.vidhyalebox.salary;

import java.math.BigDecimal;
import java.util.List;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollEntity;
import com.ms.vidhyalebox.user.UserEntity;
import com.ms.vidhyalebox.util.domain.GenericEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "salary")
public class SalaryEntity extends GenericEntity {

	@ManyToOne
	@JoinColumn(name = "orgUniqId", nullable = false)
	private OrgClientEntity school;

	@OneToOne
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private UserEntity user; 
 
    @Column(nullable = false)
    private BigDecimal basicSalary; // Basic salary amount
 
    @Column(name = "netSalary")
    private BigDecimal netSalary; // Calculated as (basicSalary + allowances - deductions)

    @Column(name = "paymentDate")
    private String paymentDate; // Example: "January 2025"

    @Column(name = "paymentStatus")
    private String paymentStatus = "PENDING"; 

    @ManyToMany
    @JoinTable(
        name = "salary_payroll", 
        joinColumns = @JoinColumn(name = "salary_id"), 
        inverseJoinColumns = @JoinColumn(name = "payroll_id")
    )
    private List<PayrollEntity> payrolls;
    
    
}
