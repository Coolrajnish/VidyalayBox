package com.ms.vidhyalebox.salary;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.payrollSettings.PayrollEntity;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SalaryDTOs extends GenericDTO {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Organization Id is mandatory")
	private String school;

	@NotEmpty(message = "User Id is mandatory")
    private String user; 

	@NotEmpty(message = "Basic salary is mandatory")
    private BigDecimal basicSalary; // Basic salary amount

	@NotEmpty(message = "Net salary is mandatory")
    private BigDecimal netSalary; // Calculated as (basicSalary + allowances - deductions)

	@NotEmpty(message = "Payment date is mandatory")
    private String paymentDate; // Example: "January 2025"

    private String paymentStatus; 

    private List<PayrollEntity> payrolls;

}
