package com.ms.vidhyalebox.expense;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ExpenseDTO extends GenericDTO {

    @NotEmpty(message = "Org unique ID is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Title is mandatory")
    private String title;

    @NotEmpty(message = "Amount is mandatory")
    private String amount;
    
    @NotEmpty(message = "Date is mandatory")
    private String date;
    
    private String descr;
    
    @NotEmpty(message = "Expense category is mandatory")
    private String expensecategoryId;
   
    private String sessionId;
    
}
