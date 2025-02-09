package com.ms.vidhyalebox.holiday;

import javax.validation.constraints.NotEmpty;

import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class HolidayDTO extends GenericDTO {

    @NotEmpty(message = "Org unique ID is mandatory")
    private String orgUniqId;

    @NotEmpty(message = "Title is mandatory")
    private String title;
    
    @NotEmpty(message = "Date is mandatory")
    private String date;
    
    private String descr;
   
    private String sessionId;
    
}
