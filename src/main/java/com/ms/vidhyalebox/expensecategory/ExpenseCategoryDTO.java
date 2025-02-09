package com.ms.vidhyalebox.expensecategory;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ExpenseCategoryDTO extends GenericDTO {

    @NotEmpty(message = "Org unique ID is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Expense name is mandatory")
    private String expenseName;

    @NotEmpty(message = "Expense description is mandatory")
    private String expenseDescr;
    
}
